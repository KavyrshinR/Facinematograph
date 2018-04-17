package ru.kavyrshin.facinematograph.presentation.presenters

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
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

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getFavouriteFilms()
    }
}