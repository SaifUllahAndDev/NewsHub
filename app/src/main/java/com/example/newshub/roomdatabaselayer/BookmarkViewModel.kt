package com.example.newshub.roomdatabaselayer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookmarkViewModel(
    private val repository: BookmarkRepository
) : ViewModel() {

    private val _allBookmarks = MutableStateFlow<List<BookmarkedArticle>>(emptyList())
    val allBookmarks : StateFlow<List<BookmarkedArticle>> = _allBookmarks

    init {
        viewModelScope.launch {
            repository.allBookmarks().collect { bookmarks ->
                _allBookmarks.value = bookmarks
            }
        }
    }

    fun toggleBookmark(article: BookmarkedArticle) {
        viewModelScope.launch {
            val isAlreadyBookmarked = repository.isBookmarked(article.url)
            if (isAlreadyBookmarked) {
                repository.deleteBookmark(article.url)
            }
            else{
                repository.insertBookmark(article)
            }
        }
    }

    fun deleteBookmark(article: BookmarkedArticle) {
        viewModelScope.launch {
            repository.deleteBookmarkArticle(article)
        }
    }
}