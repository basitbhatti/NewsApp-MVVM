package com.freshapp.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.freshapp.newsapp.api.NewsService
import com.freshapp.newsapp.models.Article
import com.freshapp.newsapp.mvvm.MainVMFactory
import com.freshapp.newsapp.mvvm.MainViewModel
import com.freshapp.newsapp.mvvm.NewsRepository
import com.freshapp.newsapp.navigation.NavGraphBuilder
import com.freshapp.newsapp.ui.NewsItem
import com.freshapp.newsapp.ui.theme.NewsAppTheme

class MainActivity : ComponentActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {

                var articles: List<Article> by remember {
                    mutableStateOf(mutableListOf())
                }

                val repository = NewsRepository(NewsService.newsService)
                viewModel = ViewModelProvider(
                    this, MainVMFactory(repository)
                ).get(MainViewModel::class.java)

                viewModel.articles.observe(this) {
                    articles = it
                }

                val navController = rememberNavController()
                NavGraphBuilder(navController = navController, articles)

            }
        }
    }
}
