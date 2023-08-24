package com.example.adhipcalendar.data

import com.example.adhipcalendar.models.AddTaskRequestBody
import com.example.adhipcalendar.models.DeleteTaskRequestBody
import com.example.adhipcalendar.models.ResponseBody
import com.example.adhipcalendar.models.UserId
import com.example.adhipcalendar.networking.ApiInterface
import com.example.adhipcalendar.utils.constansts.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val retrofitInstance: Retrofit
): ApiRepositoryInterface {

    private val apiService: ApiInterface = retrofitInstance.create(ApiInterface::class.java)

    override fun addTask(addTaskRequestBody: AddTaskRequestBody): Flow<Result<ResponseBody>> {
        return flow {
            emit(Result.Loading(true))
            try {
                val response = apiService.addTask(addTaskRequestBody)
                if(response.success == true){
                    emit(Result.Success(response))
                } else {
                    emit(Result.Failed(message = response.details ?: "Something went wrong"))
                }
            } catch (e:Exception){
                emit(Result.Exception(e))
            }
        }
    }

    override fun deleteTask(deleteTaskRequestBody: DeleteTaskRequestBody): Flow<Result<ResponseBody>> {
        return flow {
            emit(Result.Loading(true))
            try {
                val response = apiService.deleteTask(deleteTaskRequestBody)
                if(response.success == true){
                    emit(Result.Success(response))
                } else {
                    emit(Result.Failed(message = response.details ?: "Something went wrong"))
                }
            } catch (e:Exception){
                emit(Result.Exception(e))
            }
        }
    }

    override fun getTask(userId: UserId): Flow<Result<ResponseBody>> {
        return flow {
            emit(Result.Loading(true))
            try {
                val response = apiService.getTask(userId)
                if(response.success == true){
                    emit(Result.Success(response))
                } else {
                    emit(Result.Failed(message = response.details ?: "Something went wrong"))
                }
            } catch (e:Exception){
                emit(Result.Exception(e))
            }
        }
    }

}
