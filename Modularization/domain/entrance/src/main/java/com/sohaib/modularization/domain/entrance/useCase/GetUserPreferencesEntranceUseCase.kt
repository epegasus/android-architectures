package com.sohaib.modularization.domain.entrance.useCase

import com.sohaib.modularization.domain.entrance.repository.EntranceRepository

class GetUserPreferencesEntranceUseCase(private val repository: EntranceRepository) {

    suspend fun isLanguageSelected(): Boolean = repository.isLanguageSelected()
    suspend fun isOnBoardingCompleted(): Boolean = repository.isOnBoardingCompleted()

}