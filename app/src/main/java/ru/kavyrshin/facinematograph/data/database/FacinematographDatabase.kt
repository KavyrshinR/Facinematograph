package ru.kavyrshin.facinematograph.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import ru.kavyrshin.facinematograph.domain.global.models.Film

@Database(entities = arrayOf(Film::class), version = 1)
abstract class FacinematographDatabase : RoomDatabase() {

    abstract fun getFilmDao() : FilmDAO
}