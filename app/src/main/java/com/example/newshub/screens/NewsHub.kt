package com.example.newshub.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newshub.networkinglayer.RetrofitInstance
import com.example.newshub.newsrepositoryandviewmodel.NewsRepository
import com.example.newshub.newsrepositoryandviewmodel.NewsViewModel
import com.example.newshub.roomdatabaselayer.BookmarkRepository
import com.example.newshub.roomdatabaselayer.BookmarkViewModel
import com.example.newshub.roomdatabaselayer.BookmarksDatabase

sealed class Screen(val route : String) {
    object NewsScreen : Screen("news_screen")
    object BookmarksScreen : Screen("bookmarks_screen")
}


@Composable
fun NewsHub() {
    val context = LocalContext.current
    val db = BookmarksDatabase.getDatabase(context.applicationContext)
    val repo = BookmarkRepository(db.bookmarkDao())
    val bookmarkViewModel = remember {
        BookmarkViewModel(repo)
    }

    val newsViewModel = remember {
        NewsViewModel(
            repository = NewsRepository(RetrofitInstance.api)
        )
    }

    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination?.route

    Scaffold(
        bottomBar = { BottomNavBar(navController , currentDestination) }
    ) { innerPadding ->
        NavHost(
            navController = navController ,
            startDestination = Screen.NewsScreen.route ,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.NewsScreen.route) {
                MainScreen(
                    newsViewModel = newsViewModel ,
                    bookmarkViewModel = bookmarkViewModel
                )
            }
            composable(Screen.BookmarksScreen.route) {
                BookmarksScreen(
                    bookmarkViewModel = bookmarkViewModel
                )
            }
        }
    }

}