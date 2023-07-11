package com.sample.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.databinding.CharacterListFragmentLayoutBinding
import com.sample.view.adapter.CharacterListAdapter
import com.sample.viewmodel.CharactersListViewModel
import com.sample.viewmodel.CharactersListViewModelState
import com.sample.viewmodel.SharedViewModel
import org.koin.android.ext.android.inject


class CharacterListFragment : Fragment() {

    private lateinit var binding: CharacterListFragmentLayoutBinding
    private val viewModel: CharactersListViewModel by inject()
    private val sharedViewModel: SharedViewModel by inject()
    private val listAdapter = CharacterListAdapter(mutableListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CharacterListFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observeViewModel()
    }

    private fun setupUI() {
        binding.let {
            it.characterListRecyclerView.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
            it.characterListRecyclerView.layoutManager =
                LinearLayoutManager(context)
            it.characterListRecyclerView.adapter = listAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.charactersListState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is CharactersListViewModelState.Loading -> {
                    binding.characterListProgressBar.visibility = View.VISIBLE
                }
                is CharactersListViewModelState.ShowListOfCharacters -> {
                    binding.characterListProgressBar.visibility = View.GONE
                    listAdapter.updateList(state.characters)
                }
                else -> {
                    // not required
                }
            }
        }

        sharedViewModel.searchQuery.observe(viewLifecycleOwner) { query ->
            val a = query
        }
    }
}