package com.trainingandroid.data.model.moviedetail

import com.google.gson.annotations.SerializedName

data class ProductionCountryResponse(
    @SerializedName("iso_3166_1")
    val iso: String,
    @SerializedName("name")
    val name: String
)