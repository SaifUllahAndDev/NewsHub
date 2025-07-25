package com.example.newshub.screens

import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import com.example.newshub.roomdatabaselayer.BookmarkViewModel

@Composable
fun BookmarksScreen(
    bookmarkViewModel: BookmarkViewModel
) {
    val allBookmarks = bookmarkViewModel.allBookmarks.collectAsState()

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
            .background( color = MaterialTheme.colorScheme.outline )
    ) {
        BookmarksTopBar()
        LazyColumn {
            items(allBookmarks.value) { bookmark ->
                BookMarkArticleCard(
                    bookmarkArticle = bookmark ,
                    onCardClick = {
                        customTabsIntent.launchUrl(
                            context , bookmark.url.toUri()
                        )
                    } ,
                    onBookmarkClick = {
                        bookmarkViewModel.deleteBookmark(article = bookmark)
                    }
                )
            }
        }
    }
}