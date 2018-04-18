package ru.kavyrshin.facinematograph.domain.interactors

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.kavyrshin.facinematograph.data.repositories.FilmsRepository
import ru.kavyrshin.facinematograph.domain.global.models.Film
import javax.inject.Inject

class SearchFilmsInteractor public @Inject constructor(private val filmsRepository: FilmsRepository) {

    fun searchFilmsByTitle(searchRequest: String, page: Int) : Single<List<Film>> {
        return filmsRepository.search(searchRequest, page)
                .subscribeOn(Schedulers.io())
    }

    fun saveFavouriteFilm(film: Film) : Completable {
        return filmsRepository.saveFilmInFavourite(film)
                .subscribeOn(Schedulers.io())
    }
}