package com.example.adhipcalendar.utils.constansts

object Constants {

    object Network {
        // Headers
        const val ACCEPT = "Accept: application/json"
        const val CONTENT_TYPE = "Content-Type: application/json"

        // Base URL
        const val BASE_URL = "https://dev.frndapp.in:8080"

        // Endpoints
        const val STORE_TASK = "/api/storeCalendarTask"
        const val DELETE_TASK = "/api/deleteCalendarTask"
        const val GET_TASK = "/api/getCalendarTaskList"
    }
}

enum class ApiType {
    BaseUrl
}