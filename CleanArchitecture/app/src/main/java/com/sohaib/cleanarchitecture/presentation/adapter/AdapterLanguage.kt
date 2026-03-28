package com.sohaib.cleanarchitecture.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sohaib.cleanarchitecture.domain.entities.Language
import com.sohaib.cleanarchitecture.databinding.ItemLanguageBinding

class AdapterLanguage(private val clicker: (selectedCode: String) -> Unit) : ListAdapter<Language, AdapterLanguage.CustomViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLanguageBinding.inflate(layoutInflater, parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentItem = getItem(position)

        holder.binding.apply {
            mtvLanguageItemLanguage.text = currentItem.languageName
            sivTickItemLanguage.isVisible = currentItem.isSelected
            sivOverlayItemLanguage.isVisible = currentItem.isSelected
            mcvContainerItemLanguage.setOnClickListener { clicker.invoke(currentItem.languageCode) }
        }
    }

    inner class CustomViewHolder(val binding: ItemLanguageBinding) : RecyclerView.ViewHolder(binding.root)

    object DiffCallback : DiffUtil.ItemCallback<Language>() {
        override fun areItemsTheSame(oldItem: Language, newItem: Language): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Language, newItem: Language): Boolean {
            return oldItem == newItem
        }
    }
}