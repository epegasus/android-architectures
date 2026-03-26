package com.sohaib.modularization.feature.favourites.ui

import androidx.core.view.isVisible
import com.sohaib.modularization.core.base.BaseFragment
import com.sohaib.modularization.core.extensions.showToast
import com.sohaib.modularization.core.flows.repeatWhenStarted
import com.sohaib.modularization.feature.favourites.adapter.FavouritesAdapter
import com.sohaib.modularization.feature.favourites.databinding.FragmentFavouritesBinding
import com.sohaib.modularization.feature.favourites.intent.FavouritesIntent
import com.sohaib.modularization.feature.favourites.states.FavouritesDestination
import com.sohaib.modularization.feature.favourites.states.FavouritesState
import com.sohaib.modularization.feature.favourites.viewmodel.FavouritesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Favourites screen fragment
 */
class FavouritesFragment : BaseFragment<FragmentFavouritesBinding>(FragmentFavouritesBinding::inflate) {

    private val viewModel by viewModel<FavouritesViewModel>()

    private val adapter by lazy { FavouritesAdapter() }

    override fun onViewCreated() {
        setupRecyclerView()

        binding.fabSearch.setOnClickListener { viewModel.handleIntent(FavouritesIntent.SearchClicked) }
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

    private fun render(state: FavouritesState) {
        binding.cpiProgress.isVisible = state.isLoading
        binding.llEmpty.isVisible = state.favourites.isEmpty()

        adapter.submitList(state.favourites)

        state.errorMessageRes?.let {
            context.showToast(it)
            viewModel.handleIntent(FavouritesIntent.ErrorCleared)
        }

        state.navigateTo?.let {
            when (it) {
                is FavouritesDestination.FavouriteDetail -> appNavigator.openMediaDetail(it.favourite.id, it.favourite.mediaType.uppercase())
                is FavouritesDestination.Search -> context.showToast("Under Development")
            }
        }
    }
}
