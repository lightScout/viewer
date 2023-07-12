package com.sample.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sample.R
import com.sample.databinding.CharacterDetailActivityLayoutBinding
import com.sample.view.fragment.CharacterDetailFragment

class CharacterDetailActivity: AppCompatActivity() {
    private lateinit var binding: CharacterDetailActivityLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CharacterDetailActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        launchCharacterDetailsFragment()
    }

    private fun launchCharacterDetailsFragment() {
        val bundle = Bundle()
        bundle.putParcelable(CharacterDetailFragment.CHARACTER_DATA, intent.extras?.getParcelable(CharacterDetailFragment.CHARACTER_DATA))
        val fragment = CharacterDetailFragment.newInstance()
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .add(R.id.character_detail_frameLayout, fragment)
            .commit()
    }
}