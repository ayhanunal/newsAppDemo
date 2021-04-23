package com.ayhanunal.newsapp.repo

import com.ayhanunal.newsapp.api.RetrofitAPI
import com.ayhanunal.newsapp.model.NewsModel
import com.ayhanunal.newsapp.util.Resource
import java.lang.Exception
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val retrofitAPI: RetrofitAPI
) : NewsRepositoryInterface {

    override suspend fun allNews(): Resource<NewsModel> {
        return try {

            val response = retrofitAPI.allNews()
            if (response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error", null)
            } else{
                Resource.error("Error", null)
            }

        } catch (e: Exception){
            Resource.error(e.toString(), null)
        }
    }
}