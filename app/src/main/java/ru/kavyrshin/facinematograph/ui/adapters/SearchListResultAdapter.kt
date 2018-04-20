package ru.kavyrshin.facinematograph.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.film_favourite_list_item.view.*
import ru.kavyrshin.facinematograph.R
import ru.kavyrshin.facinematograph.domain.global.models.Film

class SearchListResultAdapter(
        private val itemClick: (Film) -> Unit,
        private val nextPageListener: () -> Unit)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val filmViewType: Int = 123

    var filmList: MutableList<Film> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FilmListItemViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.film_favourite_list_item, parent, false), itemClick)
    }

    override fun getItemCount(): Int {
        return filmList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            holder is FilmListItemViewHolder -> holder.bindFilm(filmList[position])
        }
        if (itemCount - position < 5) nextPageListener()
    }


    class FilmListItemViewHolder(itemView: View, private val itemClick: (Film) -> Unit) : RecyclerView.ViewHolder(itemView) {

        fun bindFilm(film: Film) {
            Picasso.get().load(film.posterSrc).error(R.drawable.broken_image).into(itemView.imageView)
            itemView.tvTitle.text = film.title
            itemView.tvYear.text = film.year.toString()
            itemView.btnFavourite.setText(R.string.save_favourite)
            itemView.btnFavourite.setOnClickListener { itemClick(film) }
        }
    }
}