package ru.kavyrshin.facinematograph.presentation.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.kavyrshin.facinematograph.domain.global.models.Film
import ru.kavyrshin.facinematograph.domain.interactors.SearchFilmsInteractor
import ru.kavyrshin.facinematograph.presentation.Paginator
import ru.kavyrshin.facinematograph.presentation.views.SearchFilmsView
import javax.inject.Inject

@InjectViewState
class SearchFilmsPresenter public @Inject constructor(private val searchInteractor: SearchFilmsInteractor)
    : BasePresenter<SearchFilmsView>() {

    private val paginator = Paginator<Film>(
            { x, y ->
                (searchInteractor.searchFilmsByTitle(x, y).observeOn(AndroidSchedulers.mainThread())) },

            object : Paginator.ViewController<Film> {

        override fun showEmptyProgress(show: Boolean) {
            Log.d("myLogs", "showEmtyProgress")
        }

        override fun showEmptyError(show: Boolean, error: Throwable?) {
            Log.d("myLogs", "showEmptyError")
        }

        override fun showEmptyView(show: Boolean) {
            Log.d("myLogs", "showEmptyView")
        }

        override fun showData(show: Boolean, data: List<Film>) {
            if (show) {
                viewState.showSearchResult(data)
            }
        }

        override fun showErrorMessage(error: Throwable) {
            Log.d("myLogs", "showErrorMessage")
        }

        override fun showPageProgress(show: Boolean) {
            Log.d("myLogs", "showPageProgress")
        }
    })

    fun search(searchString: String) {
        paginator.search(searchString)
    }

    fun loadNextPage() {
        paginator.loadNewPage()
    }

    fun saveFilmInFavourities(film: Film) {
        unsubscribeOnDestroy(
                searchInteractor.saveFavouriteFilm(film)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe( {} , {viewState.showError("Не удалось сохранить")} )
        )
    }

}