package com.ayhanunal.newsappdemo.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ayhanunal.newsappdemo.api.RetrofitAPIService
import com.ayhanunal.newsappdemo.model.NewsModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class NewsViewModel(application: Application) : BaseViewModel(application) {

    private val newsAPIService = RetrofitAPIService()
    private val disposable = CompositeDisposable()

    val news = MutableLiveData<List<NewsModel>>()
    val newsError = MutableLiveData<Boolean>()
    val newsLoading = MutableLiveData<Boolean>()

    private fun getDataFromAPI(){
        newsLoading.value = true

        disposable.add(
            newsAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<NewsModel>>(){
                    override fun onSuccess(t: List<NewsModel>) {
                        showNews(t)
                    }

                    override fun onError(e: Throwable) {
                        newsLoading.value = false
                        newsError.value = true
                        Log.e("Error", e.localizedMessage)
                    }
                })
        )
    }

    private fun showNews(newsList: List<NewsModel>){
        news.value = newsList
        newsError.value = false
        newsLoading.value = false
    }

    fun getFromAPI(){
        getDataFromAPI()
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


}