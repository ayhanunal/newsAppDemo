package com.ayhanunal.newsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ayhanunal.newsapp.R
import com.ayhanunal.newsapp.model.NewsModel
import com.ayhanunal.newsapp.view.NewsFragmentDirections
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class NewsRecyclerAdapter @Inject constructor(
    val glide: RequestManager
) : RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder>() {

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffUtil = object : DiffUtil.ItemCallback<NewsModel>() {
        override fun areItemsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
            return oldItem == newItem
        }

    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var news: List<NewsModel>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_news_row, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.newsRowImageView)
        val titleText = holder.itemView.findViewById<TextView>(R.id.newsRowTitleText)
        val cardView = holder.itemView.findViewById<CardView>(R.id.newsRowCardView)

        val curNews = news[position]
        holder.itemView.apply {
            titleText.text = curNews.title
            glide.load(curNews.main_image.url).into(imageView)

            cardView.setOnClickListener {
                findNavController().navigate(NewsFragmentDirections.actionNewsFragmentToNewsDetailsFragment(curNews.title, curNews.summary, curNews.content, curNews.main_image.url, curNews.share_url))
            }
        }
    }

    override fun getItemCount(): Int {
        return news.size
    }

}