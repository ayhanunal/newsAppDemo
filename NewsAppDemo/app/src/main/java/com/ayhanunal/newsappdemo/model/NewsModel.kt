package com.ayhanunal.newsappdemo.model

import com.google.gson.annotations.SerializedName

data class NewsModel(
    @SerializedName("uuid") val uuid : Int,
    @SerializedName("title") val title : String,
    @SerializedName("summary") val summary : String,
    @SerializedName("content") val content : String,
    @SerializedName("main_image") val main_image : MainImage,
    @SerializedName("share_url") val share_url : String
)
