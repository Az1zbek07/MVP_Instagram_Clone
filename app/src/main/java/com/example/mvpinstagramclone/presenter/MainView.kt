package com.example.mvpinstagramclone.presenter

import com.example.mvpinstagramclone.model.Photo

interface MainView {
    fun onGetImagesSuccessListener(photos: List<Photo>)
    fun onGetImagesFailureListener(error: String)
}