package ru.kavyrshin.facinematograph.data.database

import android.arch.persistence.room.*
import io.reactivex.Single
import ru.kavyrshin.facinematograph.domain.global.models.Film

@Dao
interface FilmDAO {

    @Query("SELECT * FROM film")
    fun queryAllFilms() : Single<List<Film>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllFilms(films: List<Film>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilm(film: Film)

    @Delete()
    fun deleteFilm(film: Film)
}