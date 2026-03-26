package com.sohaib.modularization.feature.media.detail.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sohaib.modularization.core.constants.Constants.TAG
import com.sohaib.modularization.core.theme.R
import com.sohaib.modularization.domain.media.detail.entity.MediaDetail
import com.sohaib.modularization.domain.media.detail.usecase.GetMediaDetailUseCase
import com.sohaib.modularization.domain.media.detail.usecase.IsMediaFavouriteUseCase
import com.sohaib.modularization.domain.media.detail.usecase.ToggleMediaFavouriteUseCase
import com.sohaib.modularization.feature.media.detail.intent.MediaDetailIntent
import com.sohaib.modularization.feature.media.detail.states.MediaDetailDestination
import com.sohaib.modularization.feature.media.detail.states.MediaDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for Media Detail screen
 */
class MediaDetailViewModel(
    private val getMediaDetailUseCase: GetMediaDetailUseCase,
    private val isMediaFavouriteUseCase: IsMediaFavouriteUseCase,
    private val toggleMediaFavouriteUseCase: ToggleMediaFavouriteUseCase
) : ViewModel() {

    private val _states = MutableStateFlow(MediaDetailState())
    val states: StateFlow<MediaDetailState> = _states.asStateFlow()

    private var mediaId: Long = 0L
    private var mediaType: String = ""

    fun handleIntent(intent: MediaDetailIntent) = viewModelScope.launch {
        when (intent) {
            is MediaDetailIntent.LoadMedia -> loadMedia(intent.mediaId, intent.mediaType)
            is MediaDetailIntent.RefreshMedia -> refreshMedia()
            is MediaDetailIntent.FavouriteClicked -> onFavouriteClicked()
            is MediaDetailIntent.ShareClicked -> onShareClicked()
            is MediaDetailIntent.InfoClicked -> onInfoClicked()
            is MediaDetailIntent.NavigationCleared -> _states.update { it.copy(navigateTo = null) }
            is MediaDetailIntent.ErrorCleared -> _states.update { it.copy(errorMessageRes = null) }
        }
    }

    private fun loadMedia(mediaId: Long, mediaType: String) = viewModelScope.launch {
        this@MediaDetailViewModel.mediaId = mediaId
        this@MediaDetailViewModel.mediaType = mediaType

        _states.update { it.copy(isLoading = true) }

        try {
            val mediaDetail = getMediaDetailUseCase(mediaId, mediaType)
            if (mediaDetail != null) {
                val updatedMediaDetail = mediaDetail.copy(
                    itemClick = { onMediaClicked(mediaDetail) },
                    favouriteClick = { onFavouriteClicked() },
                    shareClick = { onShareClicked() },
                    infoClick = { onInfoClicked() }
                )
                _states.update { it.copy(isLoading = false, mediaDetail = updatedMediaDetail) }
            } else {
                _states.update { it.copy(isLoading = false, errorMessageRes = R.string.toast_something_went_wrong) }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "MediaViewModel: loadMedia: ", ex)
            _states.update { it.copy(isLoading = false, errorMessageRes = R.string.toast_something_went_wrong) }
        }
    }

    private fun refreshMedia() = viewModelScope.launch {
        if (mediaId != 0L && mediaType.isNotEmpty()) {
            loadMedia(mediaId, mediaType)
        }
    }

    private fun onFavouriteClicked() = viewModelScope.launch {
        try {
            val mediaDetail = _states.value.mediaDetail
            if (mediaDetail != null) {
                val newFavouriteStatus = toggleMediaFavouriteUseCase(
                    mediaId = mediaDetail.id,
                    mediaType = mediaDetail.mediaType,
                    name = mediaDetail.name,
                    uri = mediaDetail.uri.toString(),
                    size = mediaDetail.size,
                    dateAdded = mediaDetail.dateAdded
                )

                val updatedMediaDetail = mediaDetail.copy(isFavourite = newFavouriteStatus)
                _states.update { it.copy(mediaDetail = updatedMediaDetail) }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "MediaViewModel: onFavouriteClicked: ", ex)
            _states.update { it.copy(errorMessageRes = R.string.toast_something_went_wrong) }
        }
    }

    private fun onShareClicked() = viewModelScope.launch {
        _states.update { it.copy(navigateTo = MediaDetailDestination.ShowShare) }
    }

    private fun onInfoClicked() = viewModelScope.launch {
        _states.update { it.copy(navigateTo = MediaDetailDestination.ShowInfo) }
    }

    private fun onMediaClicked(mediaDetail: MediaDetail) = viewModelScope.launch {
        // Handle media click if needed
    }
}