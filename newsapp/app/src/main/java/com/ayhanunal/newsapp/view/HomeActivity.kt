package com.ayhanunal.newsapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ayhanunal.newsapp.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: NewsFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentFactory
        setContentView(R.layout.activity_home)
    }
}