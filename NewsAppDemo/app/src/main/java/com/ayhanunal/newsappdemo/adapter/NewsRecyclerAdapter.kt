package com.ayhanunal.newsappdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ayhanunal.newsappdemo.R
import com.ayhanunal.newsappdemo.model.NewsModel
import com.bumptech.glide.Glide
import androidx.navigation.findNavController
import com.ayhanunal.newsappdemo.view.NewsFragmentDirections

class NewsRecyclerAdapter(val newsList: ArrayList<NewsModel> ) :  RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder>(){

    class NewsViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.custom_news_row, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.newsRowImageView)
        val titleText = holder.itemView.findViewById<TextView>(R.id.newsRowTitleText)
        val cardView = holder.itemView.findViewById<CardView>(R.id.newsRowCardView)

        val curNews = newsList[position]
        holder.itemView.apply {
            titleText.text = curNews.title
            Glide.with(holder.view.context).load(curNews.main_image.url).into(imageView)

            cardView.setOnClickListener {
                findNavController().navigate(NewsFragmentDirections.actionNewsFragmentToNewsDetailsFragment(curNews.title, curNews.summary, curNews.content, curNews.main_image.url, curNews.share_url ?: ""))
            }
        }

    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun updateNewsList(curNewsList: List<NewsModel>){
        newsList.clear()
        newsList.addAll(curNewsList)
        notifyDataSetChanged()
    }

}