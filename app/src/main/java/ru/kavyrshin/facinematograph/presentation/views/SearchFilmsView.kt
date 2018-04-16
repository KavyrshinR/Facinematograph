package ru.kavyrshin.facinematograph.presentation.views

import ru.kavyrshin.facinematograph.domain.global.models.Film

interface SearchFilmsView : BaseView {
    fun showSearchResult(searchResultList: List<Film>)
}