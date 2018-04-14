package ru.kavyrshin.facinematograph

import android.app.Application
import ru.kavyrshin.facinematograph.di.global.ApplicationComponent
import ru.kavyrshin.facinematograph.di.global.DaggerApplicationComponent

class FacinematographApplication : Application() {

    public var applicationComponent : ApplicationComponent? = null
        private set
        public get() {
            return applicationComponent ?: throw NullPointerException("Application component null")
        }

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder().application(this).build()
    }
}