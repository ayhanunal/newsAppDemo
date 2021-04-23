package com.ayhanunal.newsapp.dependencyinjection

import android.content.Context
import com.ayhanunal.newsapp.R
import com.ayhanunal.newsapp.api.RetrofitAPI
import com.ayhanunal.newsapp.repo.NewsRepository
import com.ayhanunal.newsapp.repo.NewsRepositoryInterface
import com.ayhanunal.newsapp.util.Util.BASE_URL
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class) //accessible as long as the application is running
object AppModule {

    //We want it to be reached from everywhere -> object

    //inject retrofit
    @Singleton
    @Provides
    fun injectRetrofitAPI() : RetrofitAPI{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(RetrofitAPI::class.java)
    }

    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
        )


    @Singleton
    @Provides
    fun injectNormalRepo(api: RetrofitAPI) = NewsRepository(api) as NewsRepositoryInterface


}