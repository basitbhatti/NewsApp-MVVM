package com.freshapp.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.freshapp.newsapp.api.NewsService
import com.freshapp.newsapp.models.Article
import com.freshapp.newsapp.mvvm.MainVMFactory
import com.freshapp.newsapp.mvvm.MainViewModel
import com.freshapp.newsapp.mvvm.NewsRepository
import com.freshapp.newsapp.navigation.NavGraphBuilder
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
