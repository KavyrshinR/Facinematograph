package ru.kavyrshin.facinematograph.domain.interactors

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.kavyrshin.facinematograph.data.repositories.FilmsRepository
import ru.kavyrshin.facinematograph.domain.global.models.Film
import javax.inject.Inject

class FavouriteFilmsInteractor public @Inject constructor(val filmsRepository: FilmsRepository) {

    fun getAllFavouriteFilms() : Single<List<Film>> {
        return filmsRepository.getFavouriteFilms()
                .subscribeOn(Schedulers.io())
    }

    fun deleteFavouriteFilm(film: Film) : Completable {
        return filmsRepository.deleteFilmFromFavourite(film)
                .subscribeOn(Schedulers.io())
    }
}