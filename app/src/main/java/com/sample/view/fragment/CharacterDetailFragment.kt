package com.sample.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sample.BuildConfig
import com.sample.R
import com.sample.databinding.CharacterDetailFragmentLayoutBinding
import com.sample.model.data.RelatedTopic
import com.sample.util.Constants

class CharacterDetailFragment : Fragment() {

    private lateinit var binding: CharacterDetailFragmentLayoutBinding

    companion object {
        const val IS_TABLET = "isTablet"
        const val CHARACTER_DATA = "characterData"

        fun newInstance(): CharacterDetailFragment {
            return CharacterDetailFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CharacterDetailFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()

    }

    override fun onDestroy() {
        super.onDestroy()
        if (arguments?.getBoolean(IS_TABLET) == false) {
            (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        }
    }

    private fun setupUI() {
        if (arguments?.getBoolean(IS_TABLET) == false) {
            (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        }

        (arguments?.getParcelable(CHARACTER_DATA) as RelatedTopic?).let {
            it?.let {
                if (BuildConfig.FLAVOR == Constants.SIMPSONS_FLAVOR) {
                    binding.characterImageView.setImageResource(R.drawable.simpsons)
                } else {
                    binding.characterImageView.setImageResource(R.drawable.the_wire)
                }

                binding.characterTitleTextView.text =
                    it.Text.substringBefore(Constants.DELIMITER_1).substringBefore(
                        Constants.DELIMITER_2
                    )
                binding.characterDescriptionTextView.text = it.Text
            }
        }
    }
}