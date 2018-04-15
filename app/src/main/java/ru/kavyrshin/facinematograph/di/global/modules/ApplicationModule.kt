package ru.kavyrshin.facinematograph.di.global.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.kavyrshin.facinematograph.FacinematographApplication

@Module
class ApplicationModule {

    @Provides
    fun context(application : FacinematographApplication) : Context {
        return application
    }
}