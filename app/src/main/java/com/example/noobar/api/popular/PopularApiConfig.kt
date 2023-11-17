package com.example.noobar.api.popular

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PopularApiConfig {

    const val baseUrl = "https://api.themoviedb.org/3/"

    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService(): PopularApiService {
        return getRetrofit().create(PopularApiService::class.java)
    }
}