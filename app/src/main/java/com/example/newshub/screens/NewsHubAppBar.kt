package com.example.newshub.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newshub.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsHubAppBar(
    searchQuery : String ,
    onSearchQueryChange : (String) -> Unit ,
    onSearch : () -> Unit ,
    onBack : () -> Unit
) {
    val cursiveFont = FontFamily(Font(R.font.dancing_script))
    var isSearching by rememberSaveable { mutableStateOf(false) }

    if (isSearching) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.onSecondaryContainer),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(
                onClick = {
                    isSearching = false
                    onBack()
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Cancel Search" ,
                    tint = Color.White
                )
            }
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                placeholder = { Text("Search") } ,
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = Color.White,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White ,
                    focusedTextColor = Color.White ,
                    unfocusedTextColor = Color.White
                ) ,
                modifier = Modifier
                    .background(Color(0x33FFFFFF), shape = RoundedCornerShape(8.dp))
            )
            IconButton(
                onClick =  onSearch
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Confirm Search" ,
                    tint = Color.White
                )
            }
        }

    } else {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "NewsHub" ,
                    fontFamily = cursiveFont ,
                    fontSize = 48.sp ,
                    color = Color.White
                )
            } ,
            actions = {
                IconButton(
                    onClick = { isSearching = true }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search ,
                        contentDescription = "Search" ,
                        tint = Color.White
                    )
                }
            } ,
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.onSecondaryContainer
            )
        )
    }
}