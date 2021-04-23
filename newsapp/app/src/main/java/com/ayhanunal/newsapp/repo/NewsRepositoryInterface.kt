package com.ayhanunal.newsapp.repo

import com.ayhanunal.newsapp.model.NewsModel
import com.ayhanunal.newsapp.util.Resource

interface NewsRepositoryInterface {

    suspend fun allNews() : Resource<NewsModel>

}