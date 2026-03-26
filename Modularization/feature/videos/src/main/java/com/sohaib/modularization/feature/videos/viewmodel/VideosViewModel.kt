package com.sohaib.modularization.feature.videos.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sohaib.modularization.core.constants.Constants.TAG
import com.sohaib.modularization.core.theme.R
import com.sohaib.modularization.domain.favourites.usecase.GetFavouriteStatusesUseCase
import com.sohaib.modularization.domain.favourites.usecase.ToggleFavouriteUseCase
import com.sohaib.modularization.domain.videos.entity.Video
import com.sohaib.modularization.domain.videos.usecase.GetVideosUseCase
import com.sohaib.modularization.feature.videos.intent.VideosIntent
import com.sohaib.modularization.feature.videos.states.VideosDestination
import com.sohaib.modularization.feature.videos.states.VideosState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for Videos screen
 */
class VideosViewModel(
    private val getVideosUseCase: GetVideosUseCase,
    private val toggleFavouriteUseCase: ToggleFavouriteUseCase,
    private val getFavouriteStatusesUseCase: GetFavouriteStatusesUseCase
) : ViewModel() {

    private val _states = MutableStateFlow(VideosState())
    val states: StateFlow<VideosState> = _states.asStateFlow()

    init {
        handleIntent(VideosIntent.LoadVideos)
    }

    fun handleIntent(intent: VideosIntent) = viewModelScope.launch {
        when (intent) {
            is VideosIntent.LoadVideos -> loadVideos()
            is VideosIntent.RefreshVideos -> loadVideos()
            is VideosIntent.SearchClicked -> onSearchClicked()
            is VideosIntent.NavigationCleared -> _states.update { it.copy(navigateTo = null) }
            is VideosIntent.ErrorCleared -> _states.update { it.copy(errorMessageRes = null) }
        }
    }

    private fun loadVideos() = viewModelScope.launch {
        _states.update { it.copy(isLoading = true) }

        getVideosUseCase()
            .catch {
                _states.update { it.copy(isLoading = false, videos = emptyList(), errorMessageRes = R.string.toast_something_went_wrong) }
            }
            .collect { videos ->
                // Get all favorite statuses in one batch query instead of N individual queries
                val mediaIds = videos.map { it.id }
                val favouriteStatuses = getFavouriteStatusesUseCase(mediaIds)

                val list = videos.map { video ->
                    video.copy(
                        isFavourite = favouriteStatuses[video.id] ?: false,
                        itemClick = { onVideoClicked(video) },
                        favouriteClick = { onFavouriteClicked(video) }
                    )
                }
                _states.update { it.copy(isLoading = false, videos = list) }
            }
    }

    private fun onVideoClicked(video: Video) = viewModelScope.launch {
        _states.update { it.copy(navigateTo = VideosDestination.VideoDetail(video)) }
    }

    private fun onFavouriteClicked(video: Video) = viewModelScope.launch {
        try {
            val newFavouriteStatus = toggleFavouriteUseCase(
                mediaId = video.id,
                mediaType = "video",
                name = video.name,
                uri = video.uri.toString(),
                size = video.size,
                dateAdded = video.dateAdded
            )

            // Update the UI state immediately to reflect the change
            _states.update { currentState ->
                val updatedVideos = currentState.videos.map { currentVideo ->
                    if (currentVideo.id == video.id) {
                        currentVideo.copy(isFavourite = newFavouriteStatus)
                    } else {
                        currentVideo
                    }
                }
                currentState.copy(videos = updatedVideos)
            }
        } catch (ex: Exception) {
            Log.e(TAG, "VideosViewModel: onFavouriteClicked: ", ex)
        }
    }

    private fun onSearchClicked() = viewModelScope.launch {
        _states.update { it.copy(navigateTo = VideosDestination.Search) }
    }
}