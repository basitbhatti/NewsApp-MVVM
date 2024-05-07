package com.freshapp.newsapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.freshapp.newsapp.api.NewsInterface
import com.freshapp.newsapp.models.Article

class NewsRepository(private val newsApi: NewsInterface) {

    private val newsLiveData = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>>
        get() = newsLiveData


    suspend fun getNews() {
        val result = newsApi.getHeadlines("us", 1)
        if (result?.body() != null){
            newsLiveData.postValue(result.body()!!.articles)
        }
    }

}