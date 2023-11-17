package com.example.noobar.api.rated

import com.example.noobar.response.ResponseRated
import retrofit2.Call
import retrofit2.http.GET

interface RatedApiService {
    @GET("movie/top_rated?api_key=24659842891f0877b80e3c8d6a8a5bad")
    fun getRated() : Call<ResponseRated>
}