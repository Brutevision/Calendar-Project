package com.example.adhipcalendar.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AddTaskRequestBodyParams (
    val data: AddTaskRequestBody
): Parcelable
