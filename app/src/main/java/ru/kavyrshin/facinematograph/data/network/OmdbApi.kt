package ru.kavyrshin.facinematograph.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.kavyrshin.facinematograph.domain.global.models.SearchResult

interface OmdbApi {

    @GET
    fun searchFilmsByTitle(
            @Query("apikey") apikey : String,
            @Query("type") type : String,
            @Query("s") searchRequest : String,
            @Query("page") page : Int
    ) : Single<SearchResult>
}