package com.freshapp.newsapp.api

import com.freshapp.newsapp.models.News
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//GET https://newsapi.org/v2/top-headlines?country=us&apiKey=cd0a1f02033943c7ad24661a449a8865

const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "abc-abc-abc-abc-abc-abc"

interface NewsInterface {

    @GET("/v2/top-headlines?apiKey=$API_KEY")
    suspend fun getHeadlines(
        @Query("country") country: String, @Query("page") page: Int
    ): Response<News>


}

object NewsService {
    val newsService: NewsInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newsService = retrofit.create(NewsInterface::class.java)
    }

}