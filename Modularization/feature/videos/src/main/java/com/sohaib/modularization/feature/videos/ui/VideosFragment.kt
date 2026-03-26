package com.sohaib.modularization.feature.videos.ui

import androidx.core.view.isVisible
import com.sohaib.modularization.core.base.BaseFragment
import com.sohaib.modularization.core.extensions.showToast
import com.sohaib.modularization.core.flows.repeatWhenStarted
import com.sohaib.modularization.core.mediastore.model.MediaType
import com.sohaib.modularization.feature.videos.adapter.VideosAdapter
import com.sohaib.modularization.feature.videos.databinding.FragmentVideosBinding
import com.sohaib.modularization.feature.videos.intent.VideosIntent
import com.sohaib.modularization.feature.videos.states.VideosDestination
import com.sohaib.modularization.feature.videos.states.VideosState
import com.sohaib.modularization.feature.videos.viewmodel.VideosViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Videos screen fragment
 */
class VideosFragment : BaseFragment<FragmentVideosBinding>(FragmentVideosBinding::inflate) {

    private val viewModel by viewModel<VideosViewModel>()

    private val adapter by lazy { VideosAdapter() }

    override fun onViewCreated() {
        setupRecyclerView()

        binding.fabSearch.setOnClickListener { viewModel.handleIntent(VideosIntent.SearchClicked) }
    }

    override fun onResume() {
        super.onResume()
        // Refresh videos when fragment becomes visible to get updated favorite statuses
        viewModel.handleIntent(VideosIntent.RefreshVideos)
    }

    private fun setupRecyclerView() {
        binding.rcvList.adapter = adapter
    }

    override fun initObservers() {
        repeatWhenStarted {
            viewModel.states.collect { state ->
                render(state)
            }
        }
    }

    private fun render(state: VideosState) {
        binding.cpiProgress.isVisible = state.isLoading

        adapter.submitList(state.videos)

        state.errorMessageRes?.let {
            context.showToast(it)
            viewModel.handleIntent(VideosIntent.ErrorCleared)
        }

        state.navigateTo?.let {
            when (it) {
                is VideosDestination.VideoDetail -> appNavigator.openMediaDetail(it.video.id, MediaType.VIDEO.name)
                is VideosDestination.Search -> context.showToast("Under Development")
            }
        }
    }
}