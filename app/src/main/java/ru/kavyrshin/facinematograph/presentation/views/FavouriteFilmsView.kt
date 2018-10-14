package ru.kavyrshin.facinematograph.presentation.views

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.kavyrshin.facinematograph.domain.global.models.Film

interface FavouriteFilmsView : BaseView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showFavouriteFilms(favouriteFilms: List<Film>)
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun goToDetail(film: Film)
}