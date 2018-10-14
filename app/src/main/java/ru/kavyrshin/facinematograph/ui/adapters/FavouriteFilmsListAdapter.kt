package ru.kavyrshin.facinematograph.ui.adapters

import android.support.v7.util.SortedList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.film_favourite_list_item.view.*
import ru.kavyrshin.facinematograph.R
import ru.kavyrshin.facinematograph.domain.global.models.Film

class FavouriteFilmsListAdapter(private val comparator: Comparator<Film>,
                                private val deleteClick: (Film) -> Unit,
                                private val itemClick: (Film) -> Unit)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val favouriteFilmViewType: Int = 123

    private var filmList: SortedList<Film> = SortedList(Film::class.java, object : SortedList.Callback<Film>() {

        override fun areItemsTheSame(item1: Film?, item2: Film?): Boolean {
            return item1?.id == item2?.id
        }

        override fun areContentsTheSame(oldItem: Film?, newItem: Film?): Boolean {
            return oldItem?.title.equals(newItem?.title)
                    && oldItem?.posterSrc.equals(newItem?.posterSrc)
                    && oldItem?.year == newItem?.year
        }

        override fun compare(o1: Film?, o2: Film?): Int {
            return comparator.compare(o1, o2)
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            notifyItemMoved(fromPosition, toPosition)
        }

        override fun onChanged(position: Int, count: Int) {
            notifyItemRangeChanged(position, count)
        }

        override fun onInserted(position: Int, count: Int) {
            notifyItemRangeInserted(position, count)
        }

        override fun onRemoved(position: Int, count: Int) {
            notifyItemRangeRemoved(position, count)
        }
    })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FavouriteFilmViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.film_favourite_list_item, parent, false), deleteClick, itemClick)
    }

    override fun getItemCount(): Int {
        return filmList.size()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            holder is FavouriteFilmViewHolder -> holder.bindFilm(filmList[position])
        }
    }

    fun add(data: List<Film>) {
        filmList.addAll(data)
    }

    fun remove(data: List<Film>) {
        filmList.beginBatchedUpdates()

        for (item in data) {
            filmList.remove(item)
        }

        filmList.endBatchedUpdates()
    }

    fun add(film: Film) {
        filmList.add(film)
    }

    fun remove(film: Film) {
        filmList.remove(film)
    }

    fun replaceAll(list: List<Film>) {
        filmList.replaceAll(list)
    }


    class FavouriteFilmViewHolder(itemView: View,
                                  private val deleteClick: (Film) -> Unit,
                                  private val itemClick: (Film) -> Unit)
        : RecyclerView.ViewHolder(itemView) {

        fun bindFilm(film: Film) {
            itemView.setOnClickListener { itemClick(film) }
            Picasso.get().load(film.posterSrc).error(R.drawable.broken_image).into(itemView.imageView)
            itemView.tvTitle.text = film.title
            itemView.tvYear.text = film.year.toString()
            itemView.btnFavourite.setText(R.string.delete_favourite)
            itemView.btnFavourite.setOnClickListener { deleteClick(film) }
        }
    }
}