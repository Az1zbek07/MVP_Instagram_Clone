package com.example.mvpinstagramclone.presenter

import com.example.mvpinstagramclone.model.Photo
import com.example.mvpinstagramclone.network.RetroInstance
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.InputStreamReader
import java.lang.reflect.Type

class MainPresenterImpl(private val mainView: MainView): MainPresenter {
    override suspend fun getAllImages() {
        GlobalScope.launch(Dispatchers.Main){
            val response = RetroInstance.apiService().getAllImages()
            if (response.isSuccessful){
                mainView.onGetImagesSuccessListener(response.body() as List<Photo>)
            }else{
                mainView.onGetImagesFailureListener(response.message().toString())
            }
        }
    }
}