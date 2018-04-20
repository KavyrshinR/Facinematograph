package ru.kavyrshin.facinematograph.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import ru.kavyrshin.facinematograph.R
import ru.kavyrshin.facinematograph.domain.global.models.Film
import ru.kavyrshin.facinematograph.presentation.presenters.FavouriteFilmsPresenter
import ru.kavyrshin.facinematograph.presentation.views.FavouriteFilmsView
import ru.kavyrshin.facinematograph.ui.adapters.FavouriteFilmsListAdapter
import ru.kavyrshin.facinematograph.util.setOnQueryTextListener

class FavouriteFilmsActivity : BaseActivity(), View.OnClickListener, FavouriteFilmsView {

    private lateinit var toolbar: Toolbar
    private lateinit var searchView: SearchView
    private val alphabetComparator: Comparator<Film> = Comparator({ o1, o2 -> o1.title.compareTo(o2.title) })
    private var filmsCache: MutableList<Film>? = mutableListOf()

    private var btnAddFavouriteFilm: FloatingActionButton? = null
    private var favouriteFilmsList: RecyclerView? = null
    private var favouriteAdapter: FavouriteFilmsListAdapter? = null


    @InjectPresenter
    lateinit var presenter: FavouriteFilmsPresenter

    @ProvidePresenter
    fun providePresenter(): FavouriteFilmsPresenter? {
        return application?.applicationComponent?.favouriteFilmsComponent()?.build()?.presenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite_films)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)

        searchView = findViewById(R.id.searchView)
        searchView.setBackgroundResource(R.color.white)

        searchView.setOnQueryTextListener(
                { favouriteAdapter?.replaceAll(filter(filmsCache!!, it))
                    favouriteFilmsList?.scrollToPosition(0)
                    true },
                { favouriteAdapter?.replaceAll(filter(filmsCache!!, it))
                    favouriteFilmsList?.scrollToPosition(0)
                    true }
        )

        favouriteFilmsList = findViewById(R.id.favouriteFilmsList) as RecyclerView
        btnAddFavouriteFilm = findViewById(R.id.btnAddFavouriteFilm)

        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        favouriteFilmsList?.layoutManager = linearLayoutManager
        favouriteAdapter = FavouriteFilmsListAdapter (alphabetComparator, {
            presenter.deleteFilm(it)
        })
        favouriteFilmsList?.adapter = favouriteAdapter

        btnAddFavouriteFilm?.setOnClickListener(this)
    }

    fun filter(list: MutableList<Film>, query: String) : List<Film> {
        var queryLowerCase = query.toLowerCase()

        var resultList: MutableList<Film> = mutableListOf()

        for(item in list) {
            if (item.title.toLowerCase().contains(queryLowerCase)) {
                resultList.add(item)
            }
        }

        return resultList
    }

    override fun onClick(v: View?) {
        startActivity(Intent(this, SearchFilmsActivity::class.java))
    }

    override fun showFavouriteFilms(favouriteFilms: List<Film>) {
        filmsCache?.clear()
        filmsCache?.addAll(favouriteFilms)
        favouriteAdapter?.replaceAll(favouriteFilms)
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}