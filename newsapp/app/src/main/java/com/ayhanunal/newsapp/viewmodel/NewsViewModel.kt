package com.ayhanunal.newsapp.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayhanunal.newsapp.model.NewsModel
import com.ayhanunal.newsapp.repo.NewsRepositoryInterface
import com.ayhanunal.newsapp.util.Resource
import kotlinx.coroutines.launch

class NewsViewModel @ViewModelInject constructor(
    private val repository : NewsRepositoryInterface
) : ViewModel() {

    private val news = MutableLiveData<Resource<NewsModel>>()
    val newsList : LiveData<Resource<NewsModel>>
            get() = news


    fun getNews(){
        news.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.allNews()
            news.value = response
        }
    }


}