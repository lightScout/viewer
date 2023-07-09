package com.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sample.viewmodel.CharactersListViewModel
import com.sample.viewmodel.CharactersListViewModelState
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel: CharactersListViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.charactersListState.observeForever { state ->
            when (state) {
                is CharactersListViewModelState.Loading -> {}
                else -> {}
            }
        }
    }
}