package com.example.newshub.screens


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun BottomNavBar(
    navController: NavController ,
    currentDestination : String?
    ) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.onSecondaryContainer
    ) {
        NavigationBarItem(
            selected = currentDestination == Screen.NewsScreen.route ,
            onClick = {
                if (currentDestination != Screen.NewsScreen.route) {
                    navController.navigate(Screen.NewsScreen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            } ,
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home" ,
                    tint = if (currentDestination == Screen.NewsScreen.route) MaterialTheme.colorScheme.primary else Color.White
                )
                   } ,
            label = { Text("HOME" , color = Color.White) }
        )
        NavigationBarItem(
            selected =  currentDestination == Screen.BookmarksScreen.route ,
            onClick = {
                if (currentDestination != Screen.BookmarksScreen.route) {
                    navController.navigate(Screen.BookmarksScreen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            } ,
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite
                    , contentDescription = "Bookmarks" ,
                    tint = if (currentDestination == Screen.BookmarksScreen.route) MaterialTheme.colorScheme.primary else Color.White
                )
                   } ,
            label = {
                Text(
                    "BOOKMARKS" ,
                    color = Color.White
                )
            }
        )
    }
}