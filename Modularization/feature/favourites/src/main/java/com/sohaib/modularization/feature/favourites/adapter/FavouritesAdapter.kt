package com.sohaib.modularization.feature.favourites.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sohaib.modularization.core.extensions.formatDate
import com.sohaib.modularization.core.extensions.formatFileSize
import com.sohaib.modularization.core.extensions.loadImage
import com.sohaib.modularization.domain.favourites.entity.Favourite
import com.sohaib.modularization.feature.favourites.databinding.ItemFavouriteBinding

/**
 * Adapter for favourites list
 */
class FavouritesAdapter : ListAdapter<Favourite, FavouritesAdapter.FavouriteViewHolder>(FavouriteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val binding = ItemFavouriteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FavouriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FavouriteViewHolder(private val binding: ItemFavouriteBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(favourite: Favourite) {
            binding.sivThumbnail.loadImage(favourite.uri)
            binding.mtvMediaName.text = favourite.name
            binding.mtvMediaType.text = favourite.mediaType.uppercase()
            binding.mtvMediaSize.text = favourite.size.formatFileSize()
            binding.mtvMediaDate.text = "Added ${favourite.dateFavourited.formatDate()}"

            binding.root.setOnClickListener { favourite.itemClick.invoke() }
            binding.mbFavourite.setOnClickListener { favourite.favouriteClick.invoke() }
        }
    }

    class FavouriteDiffCallback : DiffUtil.ItemCallback<Favourite>() {
        override fun areItemsTheSame(oldItem: Favourite, newItem: Favourite): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Favourite, newItem: Favourite): Boolean {
            return oldItem == newItem
        }
    }
}