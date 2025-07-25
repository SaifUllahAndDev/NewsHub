package com.example.newshub.roomdatabaselayer

import androidx.room.Delete
import kotlinx.coroutines.flow.Flow

class BookmarkRepository(
    private val dao : BookmarkDao
) {
    fun allBookmarks() : Flow<List<BookmarkedArticle>> = dao.getAllBookmarks()

    suspend fun isBookmarked(url: String): Boolean {
        return dao.getBookmarkByUrl(url) != null
    }

    suspend fun insertBookmark(article: BookmarkedArticle) {
        dao.insertBookmark(article)
    }

    suspend fun deleteBookmark( url : String) {
        dao.deleteByUrl(url)
    }

    @Delete
    suspend fun deleteBookmarkArticle(article: BookmarkedArticle) {
        dao.deleteBookmark(article)
    }

}