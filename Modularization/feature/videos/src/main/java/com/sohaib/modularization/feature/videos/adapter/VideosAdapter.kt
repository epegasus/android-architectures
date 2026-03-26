package com.sohaib.modularization.feature.videos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sohaib.modularization.core.extensions.formatDuration
import com.sohaib.modularization.core.extensions.loadImage
import com.sohaib.modularization.core.theme.R
import com.sohaib.modularization.domain.videos.entity.Video
import com.sohaib.modularization.feature.videos.databinding.ItemVideoBinding

/**
 * Adapter for videos grid
 */
class VideosAdapter : ListAdapter<Video, VideosAdapter.VideoViewHolder>(VideoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = ItemVideoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class VideoViewHolder(private val binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(video: Video) {
            binding.sivThumbnail.loadImage(video.uri)
            binding.mtvVideoName.text = video.name
            binding.mtvVideoDuration.text = video.size.formatDuration()
            binding.mbFavourite.setIconResource(if (video.isFavourite) R.drawable.ic_favorite else R.drawable.ic_favorite_border)

            binding.mbFavourite.setOnClickListener { video.favouriteClick.invoke() }
            binding.root.setOnClickListener { video.itemClick.invoke() }
        }

    }

    class VideoDiffCallback : DiffUtil.ItemCallback<Video>() {
        override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem == newItem
        }
    }
}