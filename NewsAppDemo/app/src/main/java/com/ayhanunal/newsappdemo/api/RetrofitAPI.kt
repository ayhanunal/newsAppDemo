package com.ayhanunal.newsappdemo.api

import com.ayhanunal.newsappdemo.model.NewsModel
import retrofit2.http.GET
import io.reactivex.Single

interface RetrofitAPI {

    @GET("/v2/59cc13f726000062106b773d")
    fun getNews(): Single<List<NewsModel>>

}