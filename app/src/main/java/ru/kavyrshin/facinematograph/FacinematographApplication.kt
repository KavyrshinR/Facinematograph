package ru.kavyrshin.facinematograph

import android.app.Application
import org.koin.android.ext.android.startKoin
import ru.kavyrshin.facinematograph.di.global.modules.applicationModule
import ru.kavyrshin.facinematograph.di.global.modules.dataModule
import ru.kavyrshin.facinematograph.di.global.modules.databaseModule
import ru.kavyrshin.facinematograph.di.global.modules.networkModule

class FacinematographApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(applicationModule, networkModule, databaseModule, dataModule))
    }
}