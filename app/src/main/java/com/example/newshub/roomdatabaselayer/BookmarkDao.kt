package com.example.newshub.roomdatabaselayer

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {

    @Query("SELECT * FROM bookmarked_articles")
    fun getAllBookmarks() : Flow<List<BookmarkedArticle>>

    @Query("SELECT * FROM bookmarked_articles WHERE url = :url LIMIT 1")
    suspend fun getBookmarkByUrl(url: String): BookmarkedArticle?

    @Query("DELETE FROM bookmarked_articles WHERE url = :url")
    suspend fun deleteByUrl(url: String)

    @Insert
    suspend fun insertBookmark(article: BookmarkedArticle)

    @Delete
    suspend fun deleteBookmark(article: BookmarkedArticle)

}