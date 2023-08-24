package com.example.adhipcalendar.models

import com.google.gson.annotations.SerializedName

data class ResponseBody(
    @SerializedName("status") val success: Boolean? = null,
    @SerializedName("data") val details: String? = null,
    @SerializedName("tasks") var tasks : List<Tasks>
)

data class Tasks (
    @SerializedName("task_id") var taskId : Int,
    @SerializedName("task_detail") var taskDetail : TaskDetail
)

data class TaskDetail (
    @SerializedName("title") var title : String,
    @SerializedName("description") var description : String
)