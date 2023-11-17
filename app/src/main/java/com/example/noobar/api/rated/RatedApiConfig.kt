package com.example.noobar.api.rated

import com.example.noobar.api.rated.RatedApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RatedApiConfig {

    const val baseUrl = "https://api.themoviedb.org/3/"

    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService(): RatedApiService {
        return getRetrofit().create(RatedApiService::class.java)
    }
}