package com.sohaib.modularization.data.onboarding.repository

import com.sohaib.modularization.core.sharedPreferences.SharedPrefManager
import com.sohaib.modularization.data.onboarding.dataSource.OnBoardingDataSource
import com.sohaib.modularization.data.onboarding.mapper.mapToDomain
import com.sohaib.modularization.domain.onboarding.entity.OnBoardingStep
import com.sohaib.modularization.domain.onboarding.repository.OnboardingRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

/**
 * Implementation of OnboardingRepository
 */
class OnboardingRepositoryImpl(
    private val dataSource: OnBoardingDataSource,
    private val sharedPrefManager: SharedPrefManager,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : OnboardingRepository {

    override suspend fun getOnboardingSteps(): Flow<List<OnBoardingStep>> = flow {
        emit(dataSource.getOnBoardingData().map { it.mapToDomain() })
    }.flowOn(ioDispatcher)

    override suspend fun completeOnboarding() = withContext(ioDispatcher) {
        sharedPrefManager.markOnBoardingComplete = true
    }
}