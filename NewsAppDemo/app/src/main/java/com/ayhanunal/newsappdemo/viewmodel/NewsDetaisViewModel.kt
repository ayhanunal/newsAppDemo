package com.ayhanunal.newsappdemo.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.ayhanunal.newsappdemo.model.NewsModel

class NewsDetaisViewModel(application: Application) : BaseViewModel(application) {

    val newsLiveData = MutableLiveData<NewsModel>()


}