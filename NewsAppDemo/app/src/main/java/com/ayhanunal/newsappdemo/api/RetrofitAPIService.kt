package com.ayhanunal.newsappdemo.api

import com.ayhanunal.newsappdemo.model.NewsModel
import com.ayhanunal.newsappdemo.util.Util.BASE_URL
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitAPIService {

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(RetrofitAPI::class.java)

    fun getData() : Single<List<NewsModel>> {
        return api.getNews()
    }

}