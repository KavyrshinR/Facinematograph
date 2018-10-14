package ru.kavyrshin.facinematograph.features.film_detail

import android.os.Bundle
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_activity.*
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf
import ru.kavyrshin.facinematograph.R
import ru.kavyrshin.facinematograph.domain.global.models.Film
import ru.kavyrshin.facinematograph.features.film_detail.DetailActivity.Companion.FILM_KEY_EXTRA
import ru.kavyrshin.facinematograph.ui.BaseActivity

class DetailActivity : BaseActivity(), DetailContract.View {

    @InjectPresenter
    lateinit var presenter: DetailContract.Presenter

    object Companion {
        const val FILM_KEY_EXTRA: String = "FILM_KEY_EXTRA"
    }

    @ProvidePresenter
    fun providePresenter(): DetailContract.Presenter {
        return get{ parametersOf(intent.extras.getString(FILM_KEY_EXTRA, ""))}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
    }

    override fun showFilm(film: Film) {
        Picasso.get().load(film.posterSrc).into(imageView)
        tvYear.text = "${film.year}"
        tvTitle.text = film.title
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }
}