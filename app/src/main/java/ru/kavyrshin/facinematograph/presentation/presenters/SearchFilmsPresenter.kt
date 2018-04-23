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
            if (show) {
                viewState.showLoading()
            } else {
                viewState.hideLoading()
            }
        }

        override fun showEmptyError(show: Boolean, error: Throwable?) {
            if (show) {
                viewState.showError("Empty data")
            } else {
                Log.d("myLogs", "hideEmptyError")
            }
        }

        override fun showEmptyView(show: Boolean) {
            if (show) {
                Log.d("myLogs", "showEmptyView")
            } else {
                Log.d("myLogs", "hideEmptyView")
            }
        }

        override fun showData(show: Boolean, data: List<Film>) {
            if (show) {
                Log.d("myLogs", "showData")
                viewState.showSearchResult(data)
            } else {
                Log.d("myLogs", "hideData")
            }
        }

        override fun showErrorMessage(error: Throwable) {
            viewState.showError(error.message!!)
        }

        override fun showPageProgress(show: Boolean) {
            if (show) {
                Log.d("myLogs", "showPageProgress")
            } else {
                Log.d("myLogs", "hidePageProgress")
            }
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

    override fun onDestroy() {
        super.onDestroy()
        paginator.release()
    }
}