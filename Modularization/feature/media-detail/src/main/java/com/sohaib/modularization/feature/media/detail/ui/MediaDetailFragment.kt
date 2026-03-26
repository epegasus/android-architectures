package com.sohaib.modularization.feature.media.detail.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.fragment.findNavController
import com.sohaib.modularization.core.base.BaseFragment
import com.sohaib.modularization.core.extensions.loadImage
import com.sohaib.modularization.core.extensions.showToast
import com.sohaib.modularization.core.flows.repeatWhenStarted
import com.sohaib.modularization.core.theme.R
import com.sohaib.modularization.domain.media.detail.entity.MediaDetail
import com.sohaib.modularization.feature.media.detail.databinding.FragmentMediaDetailBinding
import com.sohaib.modularization.feature.media.detail.intent.MediaDetailIntent
import com.sohaib.modularization.feature.media.detail.states.MediaDetailDestination
import com.sohaib.modularization.feature.media.detail.states.MediaDetailState
import com.sohaib.modularization.feature.media.detail.viewmodel.MediaDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Media detail screen fragment
 */
class MediaDetailFragment : BaseFragment<FragmentMediaDetailBinding>(FragmentMediaDetailBinding::inflate) {

    private val viewModel by viewModel<MediaDetailViewModel>()

    private val mediaId: Long by lazy { arguments?.getLong("mediaId") ?: 0L }
    private val mediaType: String by lazy { arguments?.getString("mediaType") ?: "" }

    private var exoPlayer: ExoPlayer? = null

    override fun onViewCreated() {
        loadMedia()

        binding.mbBack.setOnClickListener { findNavController().navigateUp() }
        binding.mbFavourite.setOnClickListener { viewModel.handleIntent(MediaDetailIntent.FavouriteClicked) }
        binding.mbShare.setOnClickListener { viewModel.handleIntent(MediaDetailIntent.ShareClicked) }
        binding.mbInfo.setOnClickListener { viewModel.handleIntent(MediaDetailIntent.InfoClicked) }
    }

    override fun onResume() {
        super.onResume()
        // Refresh media when fragment becomes visible
        viewModel.handleIntent(MediaDetailIntent.RefreshMedia)
    }

    override fun onPause() {
        super.onPause()
        exoPlayer?.pause()
    }

    private fun loadMedia() {
        viewModel.handleIntent(MediaDetailIntent.LoadMedia(mediaId, mediaType))
    }

    override fun initObservers() {
        repeatWhenStarted {
            viewModel.states.collect { state ->
                render(state)
            }
        }
    }

    private fun render(state: MediaDetailState) {
        binding.cpiProgress.isVisible = state.isLoading

        state.mediaDetail?.let { mediaDetail ->
            displayMedia(mediaDetail)
            updateFavouriteButton(mediaDetail.isFavourite)
            updateMediaInfo(mediaDetail)
        }

        state.errorMessageRes?.let {
            context.showToast(it)
            viewModel.handleIntent(MediaDetailIntent.ErrorCleared)
        }

        state.navigateTo?.let {
            when (it) {
                is MediaDetailDestination.NavigateBack -> findNavController().navigateUp()
                is MediaDetailDestination.ShowShare -> shareMedia(state.mediaDetail)
                is MediaDetailDestination.ShowInfo -> showMediaInfo(state.mediaDetail)
            }
        }
    }

    private fun displayMedia(mediaDetail: MediaDetail) {
        when (mediaDetail.mediaType.lowercase()) {
            "image" -> {
                binding.photoView.isVisible = true
                binding.playerView.isVisible = false
                binding.photoView.loadImage(mediaDetail.uri)
            }

            "video" -> {
                binding.photoView.isVisible = false
                binding.playerView.isVisible = true
                setupVideoPlayer(mediaDetail.uri)
            }
        }
    }

    private fun setupVideoPlayer(uri: Uri) {
        exoPlayer = ExoPlayer.Builder(requireContext()).build()
        binding.playerView.player = exoPlayer

        val mediaItem = MediaItem.fromUri(uri)
        exoPlayer?.setMediaItem(mediaItem)
        exoPlayer?.prepare()
        exoPlayer?.playWhenReady = true

        exoPlayer?.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                when (playbackState) {
                    Player.STATE_ENDED -> {}
                    Player.STATE_IDLE -> {}
                    Player.STATE_READY -> {
                        binding.cpiProgress.isVisible = false
                    }

                    Player.STATE_BUFFERING -> {
                        binding.cpiProgress.isVisible = true
                    }
                }
            }
        })
    }

    private fun updateFavouriteButton(isFavourite: Boolean) {
        binding.mbFavourite.setIconResource(if (isFavourite) R.drawable.ic_favorite else R.drawable.ic_favorite_border)
    }

    private fun updateMediaInfo(mediaDetail: MediaDetail) {
        binding.mtvMediaName.text = mediaDetail.name

        val sizeText = "${mediaDetail.size / (1024 * 1024)} MB"
        val resolutionText = "${mediaDetail.width}x${mediaDetail.height}"
        val dateText = java.text.SimpleDateFormat("MMM dd, yyyy", java.util.Locale.getDefault())
            .format(java.util.Date(mediaDetail.dateAdded))
        val text = "$sizeText • $resolutionText • Added $dateText"

        binding.mtvMediaInfo.text = text
    }

    private fun shareMedia(mediaDetail: MediaDetail?) {
        mediaDetail?.let { media ->
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, media.uri)
                type = media.mimeType
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            startActivity(Intent.createChooser(shareIntent, "Share ${media.name}"))
        }
    }

    private fun showMediaInfo(mediaDetail: MediaDetail?) {
        mediaDetail?.let { media ->
            val infoText = """
                Name: ${media.name}
                Size: ${media.size / (1024 * 1024)} MB
                Resolution: ${media.width}x${media.height}
                Type: ${media.mimeType}
                Added: ${
                java.text.SimpleDateFormat("MMM dd, yyyy HH:mm", java.util.Locale.getDefault())
                    .format(java.util.Date(media.dateAdded))
            }
                ${if (media.duration != null) "Duration: ${media.duration ?: (0 / 1000)}s" else ""}
            """.trimIndent()

            context.showToast(infoText)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        exoPlayer?.release()
        exoPlayer = null
    }

    companion object {
        fun newInstance(mediaId: Long, mediaType: String): MediaDetailFragment {
            return MediaDetailFragment().apply {
                arguments = Bundle().apply {
                    putLong("mediaId", mediaId)
                    putString("mediaType", mediaType)
                }
            }
        }
    }
}