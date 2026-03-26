package com.sohaib.modularization.feature.images.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sohaib.modularization.core.constants.Constants.TAG
import com.sohaib.modularization.core.theme.R
import com.sohaib.modularization.domain.favourites.usecase.GetFavouriteStatusesUseCase
import com.sohaib.modularization.domain.favourites.usecase.ToggleFavouriteUseCase
import com.sohaib.modularization.domain.images.entity.Image
import com.sohaib.modularization.domain.images.usecase.GetImagesUseCase
import com.sohaib.modularization.feature.images.intent.ImagesIntent
import com.sohaib.modularization.feature.images.states.ImagesDestination
import com.sohaib.modularization.feature.images.states.ImagesState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for Images screen
 */
class ImagesViewModel(
    private val getImagesUseCase: GetImagesUseCase,
    private val toggleFavouriteUseCase: ToggleFavouriteUseCase,
    private val getFavouriteStatusesUseCase: GetFavouriteStatusesUseCase
) : ViewModel() {

    private val _states = MutableStateFlow(ImagesState())
    val states: StateFlow<ImagesState> = _states.asStateFlow()

    init {
        handleIntent(ImagesIntent.LoadImages)
    }

    fun handleIntent(intent: ImagesIntent) = viewModelScope.launch {
        when (intent) {
            is ImagesIntent.LoadImages -> loadImages()
            is ImagesIntent.RefreshImages -> loadImages()
            is ImagesIntent.SearchClicked -> onSearchClicked()
            is ImagesIntent.NavigationCleared -> _states.update { it.copy(navigateTo = null) }
            is ImagesIntent.ErrorCleared -> _states.update { it.copy(errorMessageRes = null) }
        }
    }

    private fun loadImages() = viewModelScope.launch {
        _states.update { it.copy(isLoading = true) }

        getImagesUseCase()
            .catch {
                _states.update { it.copy(isLoading = false, images = emptyList(), errorMessageRes = R.string.toast_something_went_wrong) }
            }
            .collect { images ->
                // Get all favorite statuses in one batch query instead of N individual queries
                val mediaIds = images.map { it.id }
                val favouriteStatuses = getFavouriteStatusesUseCase(mediaIds)

                val list = images.map { image ->
                    image.copy(
                        isFavourite = favouriteStatuses[image.id] ?: false,
                        itemClick = { onImageClicked(image) },
                        favouriteClick = { onFavouriteClicked(image) }
                    )
                }
                _states.update { it.copy(isLoading = false, images = list) }
            }
    }

    private fun onImageClicked(image: Image) = viewModelScope.launch {
        _states.update { it.copy(navigateTo = ImagesDestination.ImageDetail(image)) }
    }

    private fun onFavouriteClicked(image: Image) = viewModelScope.launch {
        try {
            val newFavouriteStatus = toggleFavouriteUseCase(
                mediaId = image.id,
                mediaType = "image",
                name = image.name,
                uri = image.uri.toString(),
                size = image.size,
                dateAdded = image.dateAdded
            )

            // Update the UI state immediately to reflect the change
            _states.update { currentState ->
                val updatedImages = currentState.images.map { currentImage ->
                    if (currentImage.id == image.id) {
                        currentImage.copy(isFavourite = newFavouriteStatus)
                    } else {
                        currentImage
                    }
                }
                currentState.copy(images = updatedImages)
            }
        } catch (ex: Exception) {
            Log.e(TAG, "ImagesViewModel: onFavouriteClicked: ", ex)
        }
    }

    private fun onSearchClicked() = viewModelScope.launch {
        _states.update { it.copy(navigateTo = ImagesDestination.Search) }
    }
}