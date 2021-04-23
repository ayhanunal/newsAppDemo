package com.ayhanunal.newsapp.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ayhanunal.newsapp.R
import com.ayhanunal.newsapp.databinding.FragmentNewsBinding
import com.ayhanunal.newsapp.databinding.FragmentNewsDetailsBinding
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class NewsDetailsFragment @Inject constructor(
    val glide : RequestManager
) : Fragment(R.layout.fragment_news_details) {

    //view binding initialize
    private var fragmentBinding : FragmentNewsDetailsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //view bind
        val binding = FragmentNewsDetailsBinding.bind(view)
        fragmentBinding = binding

        //on back pressed callback
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)

    }

    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()
    }

}