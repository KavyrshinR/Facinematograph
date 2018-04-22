package ru.kavyrshin.facinematograph.domain.global.models

import com.google.gson.annotations.SerializedName

data class SearchResult(
        @SerializedName("Search") var filmsArray : List<Film>,
        @SerializedName("totalResults") val totalResults : Int,
        @SerializedName("Result") val result : Boolean,
        @SerializedName("Error") val error : String
)