package com.ayhanunal.newsappdemo.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayhanunal.newsappdemo.R
import com.ayhanunal.newsappdemo.adapter.NewsRecyclerAdapter
import com.ayhanunal.newsappdemo.databinding.FragmentNewsBinding
import com.ayhanunal.newsappdemo.viewmodel.NewsViewModel

class NewsFragment : Fragment(R.layout.fragment_news) {

    //view binding initialize
    private var fragmentBinding : FragmentNewsBinding? = null

    private lateinit var viewModel: NewsViewModel
    private val newsAdapter = NewsRecyclerAdapter(arrayListOf())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)

        //view bind
        val binding = FragmentNewsBinding.bind(view)
        fragmentBinding = binding

        viewModel.getFromAPI()

        binding.recyclerViewNews.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewNews.adapter = newsAdapter

        observeLiveData()

    }

    private fun observeLiveData(){

        viewModel.news.observe(viewLifecycleOwner, Observer { news ->
            news?.let {
                fragmentBinding?.recyclerViewNews?.visibility = View.VISIBLE
                newsAdapter.updateNewsList(news)
            }
        })

        viewModel.newsError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it) {
                    //error true
                    Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_LONG).show()
                }
            }
        })

        viewModel.newsLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it) {
                    //loading true
                    fragmentBinding?.progressBar?.visibility = View.VISIBLE
                    fragmentBinding?.recyclerViewNews?.visibility = View.GONE
                } else {
                    fragmentBinding?.progressBar?.visibility = View.GONE
                }
            }
        })


    }

}