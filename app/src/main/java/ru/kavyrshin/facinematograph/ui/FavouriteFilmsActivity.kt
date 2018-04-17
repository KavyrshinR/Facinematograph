package ru.kavyrshin.facinematograph.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import ru.kavyrshin.facinematograph.R
import ru.kavyrshin.facinematograph.domain.global.models.Film
import ru.kavyrshin.facinematograph.presentation.presenters.FavouriteFilmsPresenter
import ru.kavyrshin.facinematograph.presentation.views.FavouriteFilmsView
import ru.kavyrshin.facinematograph.ui.adapters.FavouriteFilmsListAdapter

class FavouriteFilmsActivity : BaseActivity(), View.OnClickListener, FavouriteFilmsView {

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

        favouriteFilmsList = findViewById(R.id.favouriteFilmsList) as RecyclerView
        btnAddFavouriteFilm = findViewById(R.id.btnAddFavouriteFilm) as FloatingActionButton

        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        favouriteFilmsList?.layoutManager = linearLayoutManager
        favouriteAdapter = FavouriteFilmsListAdapter {
            Toast.makeText(this, it.title, Toast.LENGTH_SHORT).show()
        }
        favouriteFilmsList?.adapter = favouriteAdapter

        btnAddFavouriteFilm?.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        startActivity(Intent(this, SearchFilmsActivity::class.java))
    }


    override fun showFavouriteFilms(favouriteFilms: List<Film>) {
        favouriteAdapter?.addData(favouriteFilms)
        favouriteAdapter?.notifyDataSetChanged()
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