package com.example.mvpinstagramclone.model

import com.google.gson.annotations.SerializedName

data class Photo(
    val id: Int,
    val title: String,
    @SerializedName("image")
    val url: String
)