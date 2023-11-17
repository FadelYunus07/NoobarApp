package com.example.noobar.api.tv

import com.example.noobar.response.ResponseTV
import retrofit2.Call
import retrofit2.http.GET

interface TvApiService {
    @GET("tv/popular?api_key=24659842891f0877b80e3c8d6a8a5bad")
    fun getTV() : Call<ResponseTV>
}