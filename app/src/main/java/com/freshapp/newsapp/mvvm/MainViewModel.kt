package com.freshapp.newsapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freshapp.newsapp.models.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(val newsRepository: NewsRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.getNews()
        }
    }

    val articles: LiveData<List<Article>>
        get() = newsRepository.articles

}