package com.freshapp.newsapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.freshapp.newsapp.models.Article
import com.freshapp.newsapp.navigation.Screen
import com.freshapp.newsapp.state.NewsState

@Composable
fun HomeScreen(navController: NavHostController, articles: List<Article>) {

    LazyColumn {
        items(articles) {
            NewsItem(article = it, navController = navController)
        }
    }
}


@Composable
fun NewsItem(modifier: Modifier = Modifier, article: Article, navController: NavHostController) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable {
                NewsState.setArticle(article)
                navController.navigate(Screen.DetailScreen.route)
            },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0x00F6F6F6))
    ) {

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = article.urlToImage,
                contentScale = ContentScale.Crop,
                contentDescription = "News Image",
                modifier = modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(10.dp))

            )

            Column(
                modifier = modifier.padding(horizontal = 15.dp)
            ) {
                Text(
                    text = article.title,
                    fontSize = 22.sp,
                    color = Color.Black,
                    maxLines = 2,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    modifier = Modifier.padding(top = 15.dp),
                    text = article?.author ?: "Okay",
                    color = Color.Gray,
                )

            }

        }


    }

}
