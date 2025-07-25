package com.example.newshub.newsrepositoryandviewmodel

import com.example.newshub.datalayer.NewsResponse
import com.example.newshub.networkinglayer.NewsApiInterface


class NewsRepository(
    private val api : NewsApiInterface
) {

    suspend fun getTopHeadlines( country : String , category : String , query : String ) : NewsResponse {
        return api.getTopHeadlines(
            country = country ,
            category = category ,
            apiKey = "f59b1766d40c4075b99452ae966bb724" ,
            query = query
        )
    }
}