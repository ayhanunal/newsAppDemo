package com.ayhanunal.newsapp.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayhanunal.newsapp.R
import com.ayhanunal.newsapp.adapter.NewsRecyclerAdapter
import com.ayhanunal.newsapp.databinding.FragmentNewsBinding
import com.ayhanunal.newsapp.model.NewsModel
import com.ayhanunal.newsapp.util.Status
import com.ayhanunal.newsapp.viewmodel.NewsViewModel
import javax.inject.Inject

class NewsFragment @Inject constructor(
    val newsRecyclerAdapter: NewsRecyclerAdapter
) : Fragment(R.layout.fragment_news){

    //view binding initialize
    private var fragmentBinding : FragmentNewsBinding? = null

    lateinit var viewModel : NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)

        //view bind
        val binding = FragmentNewsBinding.bind(view)
        fragmentBinding = binding

        viewModel.getNews()
        //subscribeToObservers()

        Log.e("AAAAAAAAAAAAAA", viewModel.newsList.value.toString())

        binding.recyclerViewNews.adapter = newsRecyclerAdapter
        binding.recyclerViewNews.layoutManager = LinearLayoutManager(requireContext())




    }

    private fun subscribeToObservers() {
        viewModel.newsList.observe(viewLifecycleOwner, Observer {
            Log.e("Status", it.status.toString())

            when (it.status) {
                Status.SUCCESS -> {
                    //val urls = it.data?.main_image?.map { imageResult ->  imageResult.url }
                    //imageRecyclerAdapter.images = urls ?: listOf()

                    //it.data.let {
                    //    Log.e("AAAAAAAAAAAAAAAA", it.toString())
                    //    newsRecyclerAdapter.news = listOf(it) as List<NewsModel>
                    //}

                    fragmentBinding?.progressBar?.visibility = View.GONE

                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message ?: "Error", Toast.LENGTH_LONG)
                        .show()
                    fragmentBinding?.progressBar?.visibility = View.GONE
                    Log.e("error", it.message.toString())

                }

                Status.LOADING -> {
                    fragmentBinding?.progressBar?.visibility = View.VISIBLE

                }
            }

        })
    }



    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()
    }

}