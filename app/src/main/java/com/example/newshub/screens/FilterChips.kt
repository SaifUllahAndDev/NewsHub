package com.example.newshub.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun FilterChips(
    categories : List<String> ,
    selectedCategory : String ,
    isSelectedChanged : (String) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(categories) { category ->
            FilterChip(
                selected = selectedCategory == category ,
                onClick = { isSelectedChanged(category) },
                label = {
                    Text(
                        text = category.replaceFirstChar { it.uppercase() } ,
                        color = if (selectedCategory == category) Color.Black else Color.White
                    )
                }
            )
        }
    }
}