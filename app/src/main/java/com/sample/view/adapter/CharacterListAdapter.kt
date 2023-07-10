package com.sample.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.sample.databinding.CharacterItemAdapterLayoutBinding
import com.sample.model.data.RelatedTopic

class CharacterListAdapter(private var charactersList: MutableList<RelatedTopic>) :
    RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {

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
            holder.binding.characterNameTextView.text = it.Text.substringBefore(" - ").substringBefore(" (")
        }
    }

    override fun getItemCount(): Int = charactersList.size

    fun updateList(charactersList: List<RelatedTopic>) {
        this.charactersList.clear()
        this.charactersList.addAll(charactersList)
        notifyDataSetChanged()
    }
}