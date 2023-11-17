package com.example.noobar.api.tv

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TvApiConfig {

    const val baseUrl = "https://api.themoviedb.org/3/"

    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService(): TvApiService {
        return getRetrofit().create(TvApiService::class.java)
    }
}