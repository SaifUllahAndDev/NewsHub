package com.example.newshub.screens

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.example.newshub.datalayer.Article
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


@Composable
fun ArticleCard(
    article: Article ,
    onCardClick : () -> Unit ,
    isBookmarked : Boolean ,
    onBookmarkClick : (Article) -> Unit
) {
    val formatter = DateTimeFormatter.ofPattern(
        "MMMM d, yyyy, h:mm a" , Locale.getDefault()
    )
    val parsedDate = OffsetDateTime.parse(article.publishedAt)
    val formattedDate = parsedDate.format(formatter)
    val context = LocalContext.current

    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(
            Intent.EXTRA_SUBJECT ,
            "Check out this article"
        )
        putExtra(
            Intent.EXTRA_TEXT ,
            "${article.title} \n\nRead more at: ${article.url}"
        )
    }
    val chooser = Intent.createChooser(shareIntent , "Share Via")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable(onClick = onCardClick)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            if (article.author != null) {
                Text(
                    text = "Author :  ${article.author}" ,
                    maxLines = 1 ,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Text(
                text = "Title  :  ${article.title}" ,
                maxLines = 2 ,
                overflow = TextOverflow.Ellipsis
            )
            if (article.description != null) {
                Text(
                    text = "Description :  ${article.description}" ,
                    maxLines = 2 ,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Text(
                text = "URL  : ${article.url}" ,
                maxLines = 1 ,
                overflow = TextOverflow.Ellipsis
            )
            AsyncImage(
                model = article.urlToImage?.toUri(),
                contentDescription = "Article Image"
            )
            Text(
                text = "Published at  : $formattedDate" ,
                maxLines = 1 ,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Content  : ${article.content}" ,
                maxLines = 1 ,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = {
                        onBookmarkClick(article)
                    }
                ) {
                    Icon(
                        imageVector = if (isBookmarked) Icons.Default.Favorite else Icons.Default.FavoriteBorder ,
                        contentDescription = "Bookmark"
                    )
                }
                IconButton(
                    onClick = {
                        context.startActivity(chooser)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share Article"
                    )
                }
            }
        }
    }
}