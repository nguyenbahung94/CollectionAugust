package com.example.mainactivity.searchdogs.error

data class ErrorResponse(
    val code: Int,
    val message: String,
    val data: Any
)