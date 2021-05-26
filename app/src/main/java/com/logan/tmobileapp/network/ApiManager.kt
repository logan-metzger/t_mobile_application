package com.logan.tmobileapp.network

import com.logan.tmobileapp.models.PageDTO
import retrofit2.create
import retrofit2.http.GET

class ApiManager {
    private val service: ApiService
    private val retrofit = RetrofitService.providesRetrofitService()

    init {
        service = retrofit.create(ApiService::class.java)
    }

    suspend fun getResults() = service.getResults()

    interface ApiService {
        @GET("test/home")
        suspend fun getResults(): PageDTO
    }
}