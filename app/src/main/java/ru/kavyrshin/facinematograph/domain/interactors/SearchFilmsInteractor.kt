package ru.kavyrshin.facinematograph.domain.interactors

import io.reactivex.Single
import ru.kavyrshin.facinematograph.data.repositories.FilmsRepository
import ru.kavyrshin.facinematograph.domain.global.models.Film

class SearchFilmsInteractor public constructor(val filmsRepository: FilmsRepository) {

    fun searchFilmsByTitle(searchRequest: String) : Single<List<Film>> {
        return filmsRepository.search(searchRequest, 1)
    }
}