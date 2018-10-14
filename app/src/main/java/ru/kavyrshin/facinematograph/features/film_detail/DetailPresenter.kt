package ru.kavyrshin.facinematograph.features.film_detail

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers

@InjectViewState
class DetailPresenter(val filmId: String,
                      val interactor: DetailContract.Interactor)
    : DetailContract.Presenter() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getFilm(filmId)
    }

    override fun getFilm(filmId: String) {
        unsubscribeOnDestroy(
            interactor.getFilm(filmId)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ viewState.showFilm(it) }, {viewState.showError(it.localizedMessage)})
        )
    }
}
