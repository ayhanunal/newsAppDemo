package com.ayhanunal.newsapp.api

import com.ayhanunal.newsapp.model.NewsModel
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitAPI {

    @GET("/v2/59cc13f726000062106b773d")
    suspend fun allNews() : Response<NewsModel>

}