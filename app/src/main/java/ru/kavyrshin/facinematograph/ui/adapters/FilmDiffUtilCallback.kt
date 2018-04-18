package ru.kavyrshin.facinematograph.ui.adapters

import android.support.v7.util.DiffUtil
import ru.kavyrshin.facinematograph.domain.global.models.Film

class FilmDiffUtilCallback(private val oldList: List<Film>, private val newList: List<Film>)
    : DiffUtil.Callback() {



    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id.equals(newList[newItemPosition].id)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title.equals(newList[newItemPosition].title)
            && oldList[oldItemPosition].posterSrc.equals(newList[newItemPosition].posterSrc)
            && oldList[oldItemPosition].year.equals(newList[newItemPosition].year)
    }
}