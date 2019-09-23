package com.example.trainingapp.models

import com.google.gson.annotations.SerializedName

data class RatingModel(
    @field:SerializedName("Value")
    val value: String? = "",

    @field:SerializedName("Source")
    val source: String? = ""
)