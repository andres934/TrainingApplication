package com.example.trainingapp.models

import com.google.gson.annotations.SerializedName

data class SearchModel(
    @field:SerializedName("Response")
    val response: String? = "",

    @field:SerializedName("totalResults")
    val totalResults: String? = "",

    @field:SerializedName("Search")
    val search: List<DataModel>? = emptyList()
)