package com.example.mvpinstagramclone.network

import android.provider.ContactsContract.Contacts.Photo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("/recipes/complexSearch")
    suspend fun getAllImages(
        @Query("apiKey") apiKey: String = "754b79e5a34b441a81e0e83a0e5286c3"
    ): Response<ArrayList<Photo>>
}