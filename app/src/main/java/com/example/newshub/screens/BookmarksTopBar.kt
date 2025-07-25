package com.example.newshub.screens

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.newshub.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarksTopBar() {
    val cursiveFont = FontFamily(Font(R.font.dancing_script))
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Bookmarks" ,
                fontFamily = cursiveFont ,
                fontSize = 48.sp ,
                color = Color.White
            )
        } ,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    )
}