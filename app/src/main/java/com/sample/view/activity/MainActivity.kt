package com.sample.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sample.R
import com.sample.databinding.ActivityMainBinding
import com.sample.view.fragment.CharacterListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        launchCharacterListFragment()
    }

    private fun launchCharacterListFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_1, CharacterListFragment())
            .commit()
    }


}