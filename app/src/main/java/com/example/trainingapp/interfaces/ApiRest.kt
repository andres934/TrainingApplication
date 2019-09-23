package com.example.trainingapp.interfaces

import com.example.trainingapp.models.DataModel
import com.example.trainingapp.models.SearchModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiRest {

    @GET
    fun getContentByName(@Url url: String): Call<SearchModel>

    @GET
    fun getContentById(@Url url: String): Call<DataModel>
}