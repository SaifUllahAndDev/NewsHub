package com.example.newshub.networkinglayer

import com.example.newshub.datalayer.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiInterface {

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country : String ,
        @Query("category") category : String ,
        @Query("apiKey") apiKey : String ,
        @Query("q") query : String
    ) : NewsResponse
}