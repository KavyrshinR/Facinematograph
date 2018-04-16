package ru.kavyrshin.facinematograph.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import ru.kavyrshin.facinematograph.R
import ru.kavyrshin.facinematograph.domain.global.models.Film
import ru.kavyrshin.facinematograph.presentation.presenters.SearchFilmsPresenter
import ru.kavyrshin.facinematograph.presentation.views.SearchFilmsView
import ru.kavyrshin.facinematograph.ui.adapters.SearchListResultAdapter

class SearchFilmsActivity : BaseActivity(), SearchFilmsView {

    private var recyclerView: RecyclerView? = null
    private var listResultAdapter: SearchListResultAdapter? = null


    @InjectPresenter
    lateinit var searchFilmsPresenter: SearchFilmsPresenter

    @ProvidePresenter
    fun provideSearchFilmsPresenter() : SearchFilmsPresenter? {
        return application?.applicationComponent?.searchFilmsComponent()?.build()?.presenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_films)

        recyclerView = findViewById(R.id.recyclerView) as? RecyclerView

        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = linearLayoutManager
        listResultAdapter = SearchListResultAdapter(
                { Toast.makeText(this, it.title, Toast.LENGTH_SHORT).show()}
        )
        recyclerView?.adapter = listResultAdapter
    }


    override fun showSearchResult(searchResultList: List<Film>) {
        listResultAdapter?.addData(searchResultList)
        listResultAdapter?.notifyDataSetChanged()
    }

    override fun showError(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}