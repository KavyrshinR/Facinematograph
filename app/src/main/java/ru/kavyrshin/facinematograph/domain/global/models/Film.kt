package ru.kavyrshin.facinematograph.domain.global.models

import com.google.gson.annotations.SerializedName

data class Film(
        @SerializedName("imdbID") val id : String,
        @SerializedName("Title") val title : String,
        @SerializedName("Year") val year : Int,
        @SerializedName("Poster") val posterSrc : String
)