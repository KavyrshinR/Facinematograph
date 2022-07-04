package ru.kavyrshin.facinematograph.domain.global.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Film(
        @PrimaryKey @SerializedName("imdbID") val id : String,
        @SerializedName("Title") val title : String,
        @SerializedName("Year") val year : Int,
        @SerializedName("Poster") val posterSrc : String
)