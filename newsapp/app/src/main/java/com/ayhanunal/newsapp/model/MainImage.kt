package com.ayhanunal.newsapp.model

import com.google.gson.annotations.SerializedName

data class MainImage(

    @SerializedName("url") val url : String,
    @SerializedName("height") val height : Int,
    @SerializedName("width") val width : Int

)
