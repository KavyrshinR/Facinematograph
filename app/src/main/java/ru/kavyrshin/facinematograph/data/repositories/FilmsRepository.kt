package ru.kavyrshin.facinematograph.data.repositories

import io.reactivex.Completable
import io.reactivex.Single
import ru.kavyrshin.facinematograph.BuildConfig
import ru.kavyrshin.facinematograph.data.database.FacinematographDatabase
import ru.kavyrshin.facinematograph.data.network.OmdbApi
import ru.kavyrshin.facinematograph.domain.global.models.Film
import ru.kavyrshin.facinematograph.domain.global.repositories.IFilmsRepository
import java.util.concurrent.Callable
import javax.inject.Inject

class FilmsRepository public @Inject constructor(val api: OmdbApi, val database: FacinematographDatabase) : IFilmsRepository {

    override fun search(searchRequest: String, page: Int): Single<List<Film>> {
        return api.searchFilmsByTitle("", BuildConfig.API_KEY, "movie", searchRequest, page)
                .flatMap { it -> Single.just(it.filmsArray) }
    }

    override fun saveFilmInFavourite(film: Film): Completable {
        return Completable.fromCallable(Callable { database.getFilmDao().insertFilm(film) })
    }

    override fun deleteFilmFromFavourite(film: Film): Completable {
        return Completable.fromCallable(Callable { database.getFilmDao().deleteFilm(film) })
    }

    override fun getFavouriteFilms(): Single<List<Film>> {
        return database.getFilmDao().queryAllFilms()
    }
}