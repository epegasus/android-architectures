package com.sohaib.modularization.domain.entrance.repository

interface EntranceRepository {
    suspend fun isLanguageSelected(): Boolean
    suspend fun isOnBoardingCompleted(): Boolean
}