package com.logan.tmobileapp.network

import com.logan.tmobileapp.extras.Constants.Companion.BASE_URL
import com.logan.tmobileapp.extras.Constants.Companion.TIMEOUT
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {
    private fun providesOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)

        val clientBuilder =
            OkHttpClient.Builder().connectTimeout(TIMEOUT, TimeUnit.SECONDS).readTimeout(
                TIMEOUT,
                TimeUnit.SECONDS
            ).writeTimeout(TIMEOUT, TimeUnit.SECONDS)

        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(httpLoggingInterceptor)

        return clientBuilder.build()
    }

    fun providesRetrofitService(): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(providesOkHttpClient())
            .build()

}