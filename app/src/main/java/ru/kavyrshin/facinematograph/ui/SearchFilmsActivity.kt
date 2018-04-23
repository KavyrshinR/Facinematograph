package ru.kavyrshin.facinematograph.ui

import android.os.Bundle
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import ru.kavyrshin.facinematograph.R
import ru.kavyrshin.facinematograph.domain.global.models.Film
import ru.kavyrshin.facinematograph.presentation.presenters.SearchFilmsPresenter
import ru.kavyrshin.facinematograph.presentation.views.SearchFilmsView
import ru.kavyrshin.facinematograph.ui.adapters.FilmDiffUtilCallback
import ru.kavyrshin.facinematograph.ui.adapters.SearchListResultAdapter
import ru.kavyrshin.facinematograph.util.setOnQueryTextListener
import java.util.concurrent.TimeUnit

class SearchFilmsActivity : BaseActivity(), SearchFilmsView {

    private lateinit var toolbar: Toolbar
    private lateinit var searchView: SearchView
    private lateinit var progressBar: ProgressBar

    private var recyclerView: RecyclerView? = null
    private var listResultAdapter: SearchListResultAdapter? = null


    @InjectPresenter
    lateinit var searchFilmsPresenter: SearchFilmsPresenter

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    @ProvidePresenter
    fun provideSearchFilmsPresenter() : SearchFilmsPresenter? {
        return application?.applicationComponent?.searchFilmsComponent()?.build()?.presenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_films)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)

        searchView = findViewById(R.id.searchView)
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)

        searchView.setBackgroundResource(R.color.white)

        val subject: PublishSubject<String> = PublishSubject.create()
        compositeDisposable.add(subject.debounce(700, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ searchFilmsPresenter.search(it); })
        )

        searchView.setOnQueryTextListener(
                change = { subject.onNext(it); true },
                submit = { subject.onNext(it); true }
        )

        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = linearLayoutManager
        listResultAdapter = SearchListResultAdapter( {
            Toast.makeText(this, it.title, Toast.LENGTH_SHORT).show()
            searchFilmsPresenter.saveFilmInFavourities(it)
        }, {
            searchFilmsPresenter.loadNextPage()
        })
        recyclerView?.adapter = listResultAdapter
    }


    override fun showSearchResult(searchResultList: List<Film>) {
        val diffUtil: FilmDiffUtilCallback = FilmDiffUtilCallback(listResultAdapter?.filmList!!.toList(), searchResultList)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(diffUtil)

        listResultAdapter?.filmList?.clear()
        listResultAdapter?.filmList?.addAll(searchResultList)

        diffResult.dispatchUpdatesTo(listResultAdapter)
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}