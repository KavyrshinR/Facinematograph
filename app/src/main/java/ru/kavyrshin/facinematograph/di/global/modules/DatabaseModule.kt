package ru.kavyrshin.facinematograph.di.global.modules

import android.arch.persistence.room.Room
import android.content.Context
import org.koin.dsl.module.module
import ru.kavyrshin.facinematograph.data.database.FacinematographDatabase


val databaseModule = module {
    single { provideDatabase(get()) }
}

fun provideDatabase(context: Context) : FacinematographDatabase {
    val database: FacinematographDatabase
            = Room.databaseBuilder(context, FacinematographDatabase::class.java, "cinemabase")
            .build()

    return database
}