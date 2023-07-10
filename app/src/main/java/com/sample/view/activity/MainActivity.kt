package com.sample.view.activity

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.sample.BuildConfig
import com.sample.R
import com.sample.databinding.ActivityMainBinding
import com.sample.util.Constants.Companion.SIMPSONS_FLAVOR
import com.sample.view.fragment.CharacterListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        launchCharacterListFragment()
        configTitle()
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
        return true
    }


}