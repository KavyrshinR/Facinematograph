package ru.kavyrshin.facinematograph.presentation.presenters

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.kavyrshin.facinematograph.domain.global.models.Film
import ru.kavyrshin.facinematograph.domain.interactors.FavouriteFilmsInteractor
import ru.kavyrshin.facinematograph.presentation.views.FavouriteFilmsView

@InjectViewState
class FavouriteFilmsPresenter constructor(val favouriteFilmsInteractor: FavouriteFilmsInteractor)
    : BasePresenter<FavouriteFilmsView>() {

    fun getFavouriteFilms() {
        unsubscribeOnDestroy(
            favouriteFilmsInteractor.getAllFavouriteFilms()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ viewState.showFavouriteFilms(it) },  { viewState.showError(it.message as String) })
        )
    }

    fun deleteFilm(film: Film) {
        unsubscribeOnDestroy(
                favouriteFilmsInteractor.deleteFavouriteFilm(film)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ getFavouriteFilms() },
                                { viewState.showError(it.message!!) })
        )
    }

    override fun attachView(view: FavouriteFilmsView?) {
        super.attachView(view)
        getFavouriteFilms()
    }
}