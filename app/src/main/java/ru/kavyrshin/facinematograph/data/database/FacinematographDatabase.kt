package ru.kavyrshin.facinematograph.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.kavyrshin.facinematograph.domain.global.models.Film

@Database(entities = arrayOf(Film::class), version = 1)
abstract class FacinematographDatabase : RoomDatabase() {

    abstract fun getFilmDao() : FilmDAO
}