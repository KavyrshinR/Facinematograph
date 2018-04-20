package ru.kavyrshin.facinematograph.presentation.presenters

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.kavyrshin.facinematograph.domain.global.models.Film
import ru.kavyrshin.facinematograph.domain.interactors.FavouriteFilmsInteractor
import ru.kavyrshin.facinematograph.presentation.views.FavouriteFilmsView
import javax.inject.Inject

@InjectViewState
class FavouriteFilmsPresenter public @Inject constructor(val favouriteFilmsInteractor: FavouriteFilmsInteractor)
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

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getFavouriteFilms()
    }
}