package com.sample.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.sample.databinding.CharacterItemAdapterLayoutBinding
import com.sample.model.data.RelatedTopic
import com.sample.util.Constants.Companion.DELIMITER_1
import com.sample.util.Constants.Companion.DELIMITER_2

class CharacterListAdapter(private var charactersList: MutableList<RelatedTopic>) :
    RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {

    private val referenceList = mutableListOf<RelatedTopic>()

    inner class ViewHolder(val binding: CharacterItemAdapterLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CharacterItemAdapterLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, android.R.anim.slide_in_left)
        charactersList[position].let {
            holder.binding.characterNameTextView.text =
                it.Text.substringBefore(DELIMITER_1).substringBefore(
                    DELIMITER_2
                )
        }
    }

    override fun getItemCount(): Int = charactersList.size

    @SuppressLint("NotifyDataSetChanged")
    fun searchCharacterList(searchTerm: String) {
        if (searchTerm.isEmpty()) {
            if (charactersList.size == referenceList.size) return
            charactersList.clear()
            charactersList.addAll(referenceList)
            notifyDataSetChanged()
        } else {
            if (charactersList.size != referenceList.size) {
                charactersList.clear()
                charactersList.addAll(referenceList)
            }
            charactersList = charactersList.filter {
                it.Text.substringBefore(DELIMITER_1).substringBefore(DELIMITER_2)
                    .contains(searchTerm, true)
            }.toMutableList()
            notifyDataSetChanged()
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(charactersList: List<RelatedTopic>) {
        this.charactersList.clear()
        referenceList.clear()
        referenceList.addAll(charactersList)
        this.charactersList.addAll(charactersList)
        notifyDataSetChanged()
    }
}