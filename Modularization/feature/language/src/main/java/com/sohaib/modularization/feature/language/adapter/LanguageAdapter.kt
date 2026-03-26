package com.sohaib.modularization.feature.language.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sohaib.modularization.domain.language.entity.Language
import com.sohaib.modularization.feature.language.databinding.ItemLanguageBinding

/**
 * Adapter for language selection list
 */
class LanguageAdapter : ListAdapter<Language, LanguageAdapter.LanguageViewHolder>(LanguageDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val binding = ItemLanguageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LanguageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class LanguageViewHolder(private val binding: ItemLanguageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(language: Language) {
            binding.mtvTitle.text = language.name
            binding.mtvBody.text = language.nativeName
            binding.sivSelection.isVisible = language.isSelected

            binding.root.setOnClickListener { language.itemClick.invoke() }
        }
    }

    class LanguageDiffCallback : DiffUtil.ItemCallback<Language>() {
        override fun areItemsTheSame(oldItem: Language, newItem: Language): Boolean = oldItem.code == newItem.code
        override fun areContentsTheSame(oldItem: Language, newItem: Language): Boolean = oldItem == newItem
    }
}