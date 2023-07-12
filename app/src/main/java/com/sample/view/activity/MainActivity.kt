package com.sample.view.activity

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sample.BuildConfig
import com.sample.R
import com.sample.databinding.ActivityMainBinding
import com.sample.util.Constants.Companion.EMPTY_QUERY
import com.sample.util.Constants.Companion.SIMPSONS_FLAVOR
import com.sample.util.hideKeyboard
import com.sample.util.showKeyboard
import com.sample.view.fragment.CharacterDetailFragment
import com.sample.view.fragment.CharacterListFragment
import com.sample.viewmodel.SharedViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val sharedViewModel: SharedViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeViewModel()
        configTitle()
        launchCharacterListFragment()
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            sharedViewModel.characterData.collectLatest { data ->
                val intent = Intent(this@MainActivity, CharacterDetailActivity::class.java)
                intent.putExtra(CharacterDetailFragment.CHARACTER_DATA, data)
                startActivity(intent)
            }
        }
    }

    private fun configTitle() {
        if (BuildConfig.FLAVOR == SIMPSONS_FLAVOR) {
            supportActionBar?.title = getString(R.string.simpsons_title)
        } else {
            supportActionBar?.title = getString(R.string.the_wire_title)
        }
    }

    private fun launchCharacterListFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_1, CharacterListFragment())
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        val a = menu?.findItem(R.id.search)?.let {
            it.actionView as SearchView
        }
        menu?.findItem(R.id.search)?.setOnActionExpandListener(object :
            MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                (item.actionView as SearchView).apply {
                    sharedViewModel.setSearchQuery(this.query.toString())
                    requestFocus()
                    this@MainActivity.showKeyboard()
                }
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                sharedViewModel.setSearchQuery(EMPTY_QUERY)
                this@MainActivity.hideKeyboard()
                return true
            }
        })


        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu?.findItem(R.id.search)?.actionView as SearchView).apply {
            this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    sharedViewModel.setSearchQuery(query ?: EMPTY_QUERY)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    sharedViewModel.setSearchQuery(newText ?: EMPTY_QUERY)
                    return true
                }
            })
            isIconifiedByDefault = false
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }
        return true
    }


}