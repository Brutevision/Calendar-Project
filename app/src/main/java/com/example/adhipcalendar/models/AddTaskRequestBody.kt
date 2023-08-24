package com.example.adhipcalendar.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AddTaskRequestBody(
    @SerializedName("user_id") var userId: Int,
    @SerializedName("task") var task: Task
) : Parcelable

@Parcelize
data class UserId(
    @SerializedName("user_id") var userId : Int,
): Parcelable

@Parcelize
data class Task (
    @SerializedName("title") var title : String,
    @SerializedName("description") var description : String

): Parcelable
