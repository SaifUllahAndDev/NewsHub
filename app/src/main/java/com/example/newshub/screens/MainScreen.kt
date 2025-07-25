package com.example.newshub.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.newshub.datalayer.Article
import com.example.newshub.newsrepositoryandviewmodel.NewsViewModel
import com.example.newshub.roomdatabaselayer.BookmarkViewModel
import com.example.newshub.roomdatabaselayer.BookmarkedArticle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    newsViewModel: NewsViewModel ,
    bookmarkViewModel: BookmarkViewModel
) {
    val allArticles = newsViewModel.allArticles.collectAsState()
    val totalResults = newsViewModel.totalResults.collectAsState()
    val isRefreshing = newsViewModel.isRefreshing.collectAsState()
    val selectedCategory = newsViewModel.selectedCategory.collectAsState()
    val bookmarkedArticles = bookmarkViewModel.allBookmarks.collectAsState()
    val filterCategories = listOf("business", "entertainment", "health", "science", "sports", "technology")
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(true) {
        newsViewModel.getTopHeadlines()
    }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.outline)
        ) {
            NewsHubAppBar(
                searchQuery = searchQuery ,
                onSearchQueryChange = { searchQuery = it } ,
                onSearch = {
                    newsViewModel.getTopHeadlines(searchQuery)
                    searchQuery = ""
                } ,
                onBack = {
                    newsViewModel.getTopHeadlines()
                    searchQuery = ""
                }
            )
            FilterChips(
                categories = filterCategories ,
                selectedCategory = selectedCategory.value ,
                isSelectedChanged = { category ->
                    newsViewModel.setCategory(category)
                }
            )
            ArticlesListScreen(
                results = totalResults.value ,
                isRefreshing = isRefreshing.value,
                onRefresh = { newsViewModel.refreshArticles() },
                allArticles = allArticles.value ,
                bookmarkedArticles = bookmarkedArticles.value,
                onBookmarkClick = { article ->
                    bookmarkViewModel.toggleBookmark(article.toBookmarked())
                }
            )
        }
}

fun Article.toBookmarked() : BookmarkedArticle {
    return BookmarkedArticle(
        author = this.author ?: "" ,
        title = this.title ,
        description = this.description ?: "" ,
        url = this.url ,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt
    )
}