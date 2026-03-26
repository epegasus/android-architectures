package com.sohaib.modularization.feature.favourites.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sohaib.modularization.core.constants.Constants.TAG
import com.sohaib.modularization.core.theme.R
import com.sohaib.modularization.domain.favourites.entity.Favourite
import com.sohaib.modularization.domain.favourites.usecase.GetFavouritesUseCase
import com.sohaib.modularization.domain.favourites.usecase.ToggleFavouriteUseCase
import com.sohaib.modularization.feature.favourites.intent.FavouritesIntent
import com.sohaib.modularization.feature.favourites.states.FavouritesDestination
import com.sohaib.modularization.feature.favourites.states.FavouritesState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for Favourites screen
 */
class FavouritesViewModel(
    private val getFavouritesUseCase: GetFavouritesUseCase,
    private val toggleFavouriteUseCase: ToggleFavouriteUseCase
) : ViewModel() {

    private val _states = MutableStateFlow(FavouritesState())
    val states: StateFlow<FavouritesState> = _states.asStateFlow()

    init {
        handleIntent(FavouritesIntent.LoadFavourites)
    }

    fun handleIntent(intent: FavouritesIntent) = viewModelScope.launch {
        when (intent) {
            is FavouritesIntent.LoadFavourites -> loadFavourites()
            is FavouritesIntent.SearchClicked -> onSearchClicked()
            is FavouritesIntent.NavigationCleared -> _states.update { it.copy(navigateTo = null) }
            is FavouritesIntent.ErrorCleared -> _states.update { it.copy(errorMessageRes = null) }
        }
    }

    private fun loadFavourites() = viewModelScope.launch {
        _states.update { it.copy(isLoading = true) }

        getFavouritesUseCase()
            .catch {
                _states.update { it.copy(isLoading = false, favourites = emptyList(), errorMessageRes = R.string.toast_something_went_wrong) }
            }
            .collect { favourites ->
                val list = favourites.map { favourite ->
                    favourite.copy(
                        itemClick = { onFavouriteClicked(favourite) },
                        favouriteClick = { onFavouriteClicked(favourite) }
                    )
                }
                _states.update { it.copy(isLoading = false, favourites = list) }
            }
    }

    private fun onFavouriteClicked(favourite: Favourite) = viewModelScope.launch {
        try {
            toggleFavouriteUseCase(
                mediaId = favourite.id,
                mediaType = favourite.mediaType,
                name = favourite.name,
                uri = favourite.uri.toString(),
                size = favourite.size,
                dateAdded = favourite.dateAdded
            )
        } catch (ex: Exception) {
            Log.e(TAG, "FavouritesViewModel: onFavouriteClicked: ", ex)
        }
    }

    private fun onSearchClicked() = viewModelScope.launch {
        _states.update { it.copy(navigateTo = FavouritesDestination.Search) }
    }
}