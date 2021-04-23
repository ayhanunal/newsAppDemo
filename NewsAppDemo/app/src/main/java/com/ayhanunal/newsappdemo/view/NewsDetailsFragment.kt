package com.ayhanunal.newsappdemo.view

import android.os.Bundle
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.ScrollView
import androidx.fragment.app.Fragment
import com.ayhanunal.newsappdemo.R
import com.ayhanunal.newsappdemo.databinding.FragmentNewsDetailsBinding
import com.ayhanunal.newsappdemo.viewmodel.NewsDetaisViewModel
import com.bumptech.glide.Glide

class NewsDetailsFragment : Fragment(R.layout.fragment_news_details) {

    //view binding initialize
    private var fragmentBinding : FragmentNewsDetailsBinding? = null

    //incoming news details
    private var newsTitle = ""
    private var newsSummary = ""
    private var newsContent = ""
    private var newsImageUrl = ""
    private var newsShareUrl = ""


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //navigation variables are defined
        arguments?.let {
            newsTitle = NewsDetailsFragmentArgs.fromBundle(it).title
            newsSummary = NewsDetailsFragmentArgs.fromBundle(it).summary
            newsContent = NewsDetailsFragmentArgs.fromBundle(it).content
            newsImageUrl = NewsDetailsFragmentArgs.fromBundle(it).imageUrl
            newsShareUrl = NewsDetailsFragmentArgs.fromBundle(it).shareUrl
        }

        //view bind
        val binding = FragmentNewsDetailsBinding.bind(view)
        fragmentBinding = binding

        //views are filled
        Glide.with(requireActivity()).load(newsImageUrl).error(R.drawable.app_icon).into(binding.imageView)
        binding.newsDetailsFragmentTitleText.text = newsTitle
        binding.newsDetailsFragmentSummaryText.text = newsSummary
        binding.newsDetailsFragmentDescText.text = Html.fromHtml(newsContent)
        binding.newsDetailsFragmentDescText.movementMethod = ScrollingMovementMethod()
    }

}