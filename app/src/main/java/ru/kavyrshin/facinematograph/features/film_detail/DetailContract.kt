package ru.kavyrshin.facinematograph.features.film_detail

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import io.reactivex.Single
import ru.kavyrshin.facinematograph.domain.global.models.Film
import ru.kavyrshin.facinematograph.presentation.presenters.BasePresenter
import ru.kavyrshin.facinematograph.presentation.views.BaseView

interface DetailContract {

    interface View : BaseView {
        @StateStrategyType(AddToEndSingleStrategy::class)
        fun showFilm(film: Film)
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun getFilm(filmId: String)
    }

    interface Interactor {
        fun getFilm(filmId: String): Single<Film>
    }
}