package ru.kavyrshin.facinematograph.di.global.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.kavyrshin.facinematograph.data.database.FacinematographDatabase
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context) : FacinematographDatabase {
        val database: FacinematographDatabase
                = Room.databaseBuilder(context, FacinematographDatabase::class.java, "cinemabase")
                .build()

        return database
    }
}