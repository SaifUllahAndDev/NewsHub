package com.example.newshub.newsrepositoryandviewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newshub.datalayer.Article
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsViewModel(
    val repository: NewsRepository
) : ViewModel() {

    private val _allArticles = MutableStateFlow<List<Article>>(emptyList())
    val allArticles : StateFlow<List<Article>> = _allArticles

    private val _totalResults = MutableStateFlow(0)
    val totalResults : StateFlow<Int> = _totalResults

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing : StateFlow<Boolean> = _isRefreshing

    private val _selectedCateogry = MutableStateFlow("business")
    val selectedCategory : StateFlow<String> = _selectedCateogry

    fun getTopHeadlines(query : String = "") {
        viewModelScope.launch {
            try {
                _isRefreshing.value = true
                val response = repository.getTopHeadlines(
                    country = "us" ,
                    category = _selectedCateogry.value ,
                    query = query
                )
                _allArticles.value = response.articles
                _totalResults.value = response.totalResults
                Log.d("NewsViewModel" , "Success")
                delay(1500)
                _isRefreshing.value = false
            }
            catch (e : Exception) {
                Log.e("NewsViewModel" , e.message.toString())
            }

        }
    }

    fun setCategory( category : String ) {
        _selectedCateogry.value = category
        getTopHeadlines()
    }

    fun refreshArticles() {
        viewModelScope.launch {
            _isRefreshing.value = true
            getTopHeadlines()
            delay(1500)
            _isRefreshing.value = false
        }
    }

}