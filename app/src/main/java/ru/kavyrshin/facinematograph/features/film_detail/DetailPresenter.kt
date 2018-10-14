package ru.kavyrshin.facinematograph.features.film_detail

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.kavyrshin.facinematograph.presentation.presenters.BasePresenter

@InjectViewState
class DetailPresenter(val filmId: Int,
                      val interactor: DetailContract.Interactor)
    : BasePresenter<DetailContract.View>(), DetailContract.Presenter {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getFilm(filmId)
    }

    override fun getFilm(filmId: Int) {
        unsubscribeOnDestroy(
            interactor.getFilm(filmId)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ viewState.showFilm(it) }, {viewState.showError(it.localizedMessage)})
        )
    }
}
