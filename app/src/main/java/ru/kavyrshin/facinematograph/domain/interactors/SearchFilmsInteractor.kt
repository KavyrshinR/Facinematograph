package ru.kavyrshin.facinematograph.domain.interactors

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.kavyrshin.facinematograph.data.repositories.FilmsRepository
import ru.kavyrshin.facinematograph.domain.global.models.Film
import javax.inject.Inject

class SearchFilmsInteractor public @Inject constructor(val filmsRepository: FilmsRepository) {

    fun searchFilmsByTitle(searchRequest: String) : Single<List<Film>> {
        return filmsRepository.search(searchRequest, 1)
                .subscribeOn(Schedulers.io())
    }
}