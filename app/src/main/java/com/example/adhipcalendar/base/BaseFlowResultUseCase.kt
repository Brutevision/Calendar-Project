package com.example.adhipcalendar.base

import com.example.adhipcalendar.utils.constansts.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

interface BaseUseCase<in P, R> {
    suspend operator fun invoke(params: P) : R
}