package com.example.newshub.datalayer

data class NewsResponse(
    val status : String ,
    val totalResults : Int ,
    val articles : List<Article>
)
