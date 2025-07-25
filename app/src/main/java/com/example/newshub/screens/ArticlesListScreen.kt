package com.example.newshub.screens

import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.newshub.datalayer.Article
import com.example.newshub.roomdatabaselayer.BookmarkedArticle
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticlesListScreen(
    results : Int,
    isRefreshing : Boolean,
    onRefresh : () -> Unit,
    allArticles : List<Article>,
    bookmarkedArticles: List<BookmarkedArticle>,
    onBookmarkClick : (Article) -> Unit
) {
    val refreshState = rememberPullToRefreshState()
    var refreshTrigger by remember { mutableIntStateOf(0) }
    var visible = remember(refreshTrigger) { mutableStateOf(false) }
    var isRefresh by remember { mutableStateOf(isRefreshing) }
    val context = LocalContext.current

    val customTabColorSchemeParams = CustomTabColorSchemeParams
        .Builder()
        .setToolbarColor(MaterialTheme.colorScheme.primary.toArgb())
        .build()

    val customTabsIntent = CustomTabsIntent
        .Builder()
        .setDefaultColorSchemeParams(customTabColorSchemeParams)
        .setShowTitle(true)
        .build()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Total Results : $results",
            color = Color.White
        )
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            if (isRefreshing) {
                CircularProgressIndicator()
            } else {
                PullToRefreshBox(
                    isRefreshing = isRefresh,
                    state = refreshState,
                    onRefresh = {
                        isRefresh = true
                        onRefresh()
                        isRefresh = false
                        refreshTrigger++
                    }
                ) {
                    LazyColumn {
                        itemsIndexed(allArticles) {index , article  ->
                            val isBookmarked = bookmarkedArticles.any { it.url == article.url }
                            LaunchedEffect(refreshTrigger) {
                                delay(index * 200L)
                                visible.value = true
                            }
                            Column {
                                AnimatedVisibility(
                                    visible = visible.value ,
                                    enter = slideInVertically(
                                        initialOffsetY = { it / 2 } ,
                                        animationSpec = tween(1000)
                                    ) + fadeIn(animationSpec = tween(1000))
                                ) {
                                    ArticleCard(
                                        article = article ,
                                        onCardClick = {
                                            customTabsIntent.launchUrl(
                                                context, article.url.toUri()
                                            )
                                        } ,
                                        isBookmarked = isBookmarked,
                                        onBookmarkClick = {
                                            onBookmarkClick(article)
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}