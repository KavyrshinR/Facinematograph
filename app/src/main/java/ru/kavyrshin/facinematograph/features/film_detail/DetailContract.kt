package ru.kavyrshin.facinematograph.features.film_detail

import io.reactivex.Single
import ru.kavyrshin.facinematograph.domain.global.models.Film
import ru.kavyrshin.facinematograph.presentation.views.BaseView

interface DetailContract {

    interface View : BaseView {
        fun showFilm(film: Film)
    }

    interface Presenter {
        fun getFilm(filmId: Int)
    }

    interface Interactor {
        fun getFilm(filmId: Int): Single<Film>
    }
}