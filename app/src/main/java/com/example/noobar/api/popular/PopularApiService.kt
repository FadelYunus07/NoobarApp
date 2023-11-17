package com.example.noobar.api.popular

import com.example.noobar.response.ResponseFavorite
import retrofit2.Call
import retrofit2.http.GET

interface PopularApiService {
    @GET("movie/popular?api_key=24659842891f0877b80e3c8d6a8a5bad")
    fun getFavorite() : Call<ResponseFavorite>
}