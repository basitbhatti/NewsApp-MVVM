package com.freshapp.newsapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.freshapp.newsapp.state.NewsState

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(Brush.sweepGradient(listOf(Color.White, Color.Gray)))
) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                AsyncImage(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .padding(20.dp),
                    model = NewsState.currentArticle.urlToImage,
                    contentDescription = "News Image"
                )

                Text(
                    text = NewsState.currentArticle.title,
                    fontSize = 22.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    modifier = Modifier.padding(top = 15.dp),
                    text = NewsState.currentArticle?.author ?: "Okay",
                    color = Color.Gray,
                )

            }
        }
    }
}


@Preview
@Composable
private fun Preview() {
    DetailScreen()
}