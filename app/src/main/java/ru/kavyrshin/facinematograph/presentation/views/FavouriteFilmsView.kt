package ru.kavyrshin.facinematograph.presentation.views

import ru.kavyrshin.facinematograph.domain.global.models.Film

interface FavouriteFilmsView : BaseView {

    fun showFavouriteFilms(favouriteFilms: List<Film>)
    fun goToDetail(film: Film)
}