package com.example.adhipcalendar.models

import com.google.gson.annotations.SerializedName

data class DeleteTaskRequestBody(
    @SerializedName("user_id") var userId : Int,
    @SerializedName("task_id") var taskId : Int
)
