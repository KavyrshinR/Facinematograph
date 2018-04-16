package ru.kavyrshin.facinematograph.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import ru.kavyrshin.facinematograph.R
import ru.kavyrshin.facinematograph.domain.global.models.Film

class SearchListResultAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val filmViewType: Int = 123

    var filmList: MutableList<Film>? = mutableListOf()

    fun addData(data: List<Film>) {
        filmList?.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return FilmListItemViewHolder(LayoutInflater.from(parent?.context)
                .inflate(R.layout.film_favourite_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return filmList!!.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when {
            holder is FilmListItemViewHolder -> holder.bindFilm(filmList!![position])
        }
    }


    class FilmListItemViewHolder(
            itemView: View,
            val imageView: ImageView = itemView.findViewById(R.id.imageView),
            val tvTitle: TextView = itemView.findViewById(R.id.tvTitle),
            val tvYear: TextView = itemView.findViewById(R.id.tvYear)

    ) : RecyclerView.ViewHolder(itemView) {

        fun bindFilm(film: Film) {
            Picasso.get().load(film.posterSrc).into(imageView)
            tvTitle.text = film.title
            tvYear.text = film.year.toString()
        }
    }
}