package ru.kavyrshin.facinematograph.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import ru.kavyrshin.facinematograph.domain.global.models.SearchResult

interface OmdbApi {

    @GET
    fun searchFilmsByTitle(
            @Url emptyUrl: String,
            @Query("apikey") apikey: String,
            @Query("type") type: String,
            @Query("s") searchRequest: String,
            @Query("page") page: Int
    ) : Single<SearchResult>
}