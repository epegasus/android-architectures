package com.sohaib.modularization.feature.images.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sohaib.modularization.core.extensions.formatFileSize
import com.sohaib.modularization.core.extensions.loadImage
import com.sohaib.modularization.core.theme.R
import com.sohaib.modularization.domain.images.entity.Image
import com.sohaib.modularization.feature.images.databinding.ItemImageBinding

/**
 * Adapter for images grid
 */
class ImagesAdapter : ListAdapter<Image, ImagesAdapter.ImageViewHolder>(ImageDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ImageViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(image: Image) {
            binding.sivThumbnail.loadImage(image.uri)
            binding.mtvImageSize.text = image.name
            binding.mtvImageSize.text = image.size.formatFileSize()
            binding.mbFavourite.setIconResource(if (image.isFavourite) R.drawable.ic_favorite else R.drawable.ic_favorite_border)

            binding.mbFavourite.setOnClickListener { image.favouriteClick.invoke() }
            binding.root.setOnClickListener { image.itemClick.invoke() }
        }
    }

    class ImageDiffCallback : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }
    }
}
