package com.sohaib.modularization.feature.images.ui

import androidx.core.view.isVisible
import com.sohaib.modularization.core.base.BaseFragment
import com.sohaib.modularization.core.extensions.showToast
import com.sohaib.modularization.core.flows.repeatWhenStarted
import com.sohaib.modularization.core.mediastore.model.MediaType
import com.sohaib.modularization.feature.images.adapter.ImagesAdapter
import com.sohaib.modularization.feature.images.databinding.FragmentImagesBinding
import com.sohaib.modularization.feature.images.intent.ImagesIntent
import com.sohaib.modularization.feature.images.states.ImagesDestination
import com.sohaib.modularization.feature.images.states.ImagesState
import com.sohaib.modularization.feature.images.viewmodel.ImagesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Images screen fragment
 */
class ImagesFragment : BaseFragment<FragmentImagesBinding>(FragmentImagesBinding::inflate) {

    private val viewModel by viewModel<ImagesViewModel>()

    private val adapter by lazy { ImagesAdapter() }

    override fun onViewCreated() {
        setupRecyclerView()

        binding.fabSearch.setOnClickListener { viewModel.handleIntent(ImagesIntent.SearchClicked) }
    }

    override fun onResume() {
        super.onResume()
        // Refresh images when fragment becomes visible to get updated favorite statuses
        viewModel.handleIntent(ImagesIntent.RefreshImages)
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

    private fun render(state: ImagesState) {
        binding.cpiProgress.isVisible = state.isLoading

        adapter.submitList(state.images)

        state.errorMessageRes?.let {
            context.showToast(it)
            viewModel.handleIntent(ImagesIntent.ErrorCleared)
        }

        state.navigateTo?.let {
            when (it) {
                is ImagesDestination.ImageDetail -> appNavigator.openMediaDetail(it.image.id, MediaType.IMAGE.name)
                is ImagesDestination.Search -> context.showToast("Under Development")
            }
            viewModel.handleIntent(ImagesIntent.NavigationCleared)
        }
    }
}