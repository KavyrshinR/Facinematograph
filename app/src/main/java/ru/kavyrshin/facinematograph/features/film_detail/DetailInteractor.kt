package ru.kavyrshin.facinematograph.features.film_detail

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.kavyrshin.facinematograph.domain.global.models.Film
import ru.kavyrshin.facinematograph.domain.global.repositories.IFilmsRepository

class DetailInteractor(val filmsRepository: IFilmsRepository) : DetailContract.Interactor {

    override fun getFilm(filmId: String): Single<Film> {
        return filmsRepository.getFilmById(filmId)
                .subscribeOn(Schedulers.io())
    }
}