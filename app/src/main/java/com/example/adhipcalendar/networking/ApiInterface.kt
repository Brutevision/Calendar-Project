package com.example.adhipcalendar.networking

import com.example.adhipcalendar.models.AddTaskRequestBody
import com.example.adhipcalendar.models.DeleteTaskRequestBody
import com.example.adhipcalendar.models.ResponseBody
import com.example.adhipcalendar.models.Task
import com.example.adhipcalendar.models.UserId
import com.example.adhipcalendar.utils.constansts.Constants
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {

    @POST(Constants.Network.STORE_TASK)
    @Headers(Constants.Network.ACCEPT, Constants.Network.CONTENT_TYPE)
    suspend fun addTask(@Body requestBody: AddTaskRequestBody) : ResponseBody

    @POST(Constants.Network.DELETE_TASK)
    @Headers(Constants.Network.ACCEPT, Constants.Network.CONTENT_TYPE)
    suspend fun deleteTask(@Body requestBody: DeleteTaskRequestBody) : ResponseBody

    @POST(Constants.Network.GET_TASK)
    @Headers(Constants.Network.ACCEPT, Constants.Network.CONTENT_TYPE)
    suspend fun getTask(@Body requestBody: UserId) : ResponseBody
}