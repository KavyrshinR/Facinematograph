package ru.kavyrshin.facinematograph.domain.global.repositories

import io.reactivex.Completable
import io.reactivex.Single
import ru.kavyrshin.facinematograph.domain.global.models.Film

interface IFilmsRepository {
    fun search(searchRequest: String, page: Int) : Single<List<Film>>
    fun saveFilmInFavourite(film: Film) : Completable
    fun deleteFilmFromFavourite(film: Film) : Completable
    fun getFavouriteFilms() : Single<List<Film>>
    fun getFilmById(filmId: String) : Single<Film>
}