package com.example.mainactivity.searchdogs.main.network

import com.example.mainactivity.searchdogs.main.model.ApiResponse
import retrofit2.http.GET

interface MainActivityApi {

    @GET("breeds/image/random")
    suspend fun getRandomImageByUrl(): ApiResponse<String>
}