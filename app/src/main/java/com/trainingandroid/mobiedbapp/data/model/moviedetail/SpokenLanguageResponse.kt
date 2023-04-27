package com.trainingandroid.mobiedbapp.data.model.moviedetail

import com.google.gson.annotations.SerializedName

data class SpokenLanguageResponse(
    @SerializedName("english_name")
    val englishName: String,
    @SerializedName("iso_639_1")
    val iso: String,
    @SerializedName("name")
    val name: String
)