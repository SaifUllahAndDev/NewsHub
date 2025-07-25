package com.example.newshub.roomdatabaselayer

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarked_articles")
data class BookmarkedArticle(
    val author : String?,
    val title : String,
    val description : String?,
    @PrimaryKey val url : String,
    val urlToImage : String?,
    val publishedAt : String
)
