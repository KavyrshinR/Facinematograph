package ru.kavyrshin.facinematograph.presentation.presenters

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.kavyrshin.facinematograph.domain.global.models.Film
import ru.kavyrshin.facinematograph.domain.interactors.SearchFilmsInteractor
import ru.kavyrshin.facinematograph.presentation.views.SearchFilmsView
import javax.inject.Inject

@InjectViewState
class SearchFilmsPresenter public @Inject constructor(val searchInteractor: SearchFilmsInteractor)
    : BasePresenter<SearchFilmsView>() {

    fun searchFilms(searchRequest: String) {
        unsubscribeOnDestroy(
            searchInteractor.searchFilmsByTitle(searchRequest)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ viewState.showSearchResult(it) },
                            { viewState.showError(it.message as String)})
        )
    }

    fun saveFilmInFavourities(film: Film) {
        unsubscribeOnDestroy(
                searchInteractor.saveFavouriteFilm(film)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe( {} , {viewState.showError("Не удалось сохранить")} )
        )
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        searchFilms("Friends")
    }
}