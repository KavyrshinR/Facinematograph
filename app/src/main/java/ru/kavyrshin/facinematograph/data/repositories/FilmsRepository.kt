package ru.kavyrshin.facinematograph.data.repositories

import io.reactivex.Completable
import io.reactivex.Single
import ru.kavyrshin.facinematograph.BuildConfig
import ru.kavyrshin.facinematograph.data.network.OmdbApi
import ru.kavyrshin.facinematograph.domain.global.models.Film
import ru.kavyrshin.facinematograph.domain.global.repositories.IFilmsRepository
import javax.inject.Inject

class FilmsRepository public @Inject constructor(val api: OmdbApi) : IFilmsRepository {

    override fun search(searchRequest: String, page: Int): Single<List<Film>> {
        return api.searchFilmsByTitle(BuildConfig.API_KEY, "movie", searchRequest, page)
                .flatMap { it -> Single.just(it.filmsArray) }
    }

    override fun saveFilmInFavourite(film: Film): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteFilmFromFavourite(film: Film): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFavouriteFilms(): Single<List<Film>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}