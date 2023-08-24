package com.example.adhipcalendar.usecase

import com.example.adhipcalendar.base.BaseUseCase
import com.example.adhipcalendar.data.ApiRepositoryInterface
import com.example.adhipcalendar.models.AddTaskRequestBody
import com.example.adhipcalendar.models.AddTaskRequestBodyParams
import com.example.adhipcalendar.models.ResponseBody
import com.example.adhipcalendar.utils.constansts.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias AddTaskBaseUseCase = BaseUseCase<AddTaskRequestBody, Flow<Result<ResponseBody>>>

class AddTaskUseCase @Inject constructor(
    private val repository: ApiRepositoryInterface
) : AddTaskBaseUseCase {
    override suspend operator fun invoke(params: AddTaskRequestBody) = repository.addTask(params)
}

