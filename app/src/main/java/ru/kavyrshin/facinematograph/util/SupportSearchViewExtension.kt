package ru.kavyrshin.facinematograph.util

import android.support.v7.widget.SearchView

fun SearchView.setOnQueryTextListener(change: (String) -> Boolean, submit: (String) -> Boolean) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextChange(newText: String): Boolean {
            return@onQueryTextChange change(newText)
        }

        override fun onQueryTextSubmit(query: String): Boolean {
            return@onQueryTextSubmit submit(query)
        }
    })
}