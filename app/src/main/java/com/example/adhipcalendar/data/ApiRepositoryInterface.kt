package com.example.adhipcalendar.data

import com.example.adhipcalendar.models.AddTaskRequestBody
import com.example.adhipcalendar.models.DeleteTaskRequestBody
import com.example.adhipcalendar.models.ResponseBody
import com.example.adhipcalendar.models.UserId
import com.example.adhipcalendar.utils.constansts.Result
import kotlinx.coroutines.flow.Flow

interface ApiRepositoryInterface {

    fun addTask(addTaskRequestBody: AddTaskRequestBody) : Flow<Result<ResponseBody>>

    fun deleteTask(deleteTaskRequestBody: DeleteTaskRequestBody) : Flow<Result<ResponseBody>>

    fun getTask(userId: UserId) : Flow<Result<ResponseBody>>

}