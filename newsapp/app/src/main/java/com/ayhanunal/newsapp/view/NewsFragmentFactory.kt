package com.ayhanunal.newsapp.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.ayhanunal.newsapp.adapter.NewsRecyclerAdapter
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class NewsFragmentFactory @Inject constructor(
    private val newsRecyclerAdapter: NewsRecyclerAdapter,
    private val glide : RequestManager
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when(className){
            NewsFragment::class.java.name -> NewsFragment(newsRecyclerAdapter)
            NewsDetailsFragment::class.java.name -> NewsDetailsFragment(glide)
            else -> super.instantiate(classLoader, className)
        }

    }

}