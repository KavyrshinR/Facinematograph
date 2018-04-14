package ru.kavyrshin.facinematograph.di.global

import dagger.BindsInstance
import dagger.Component
import ru.kavyrshin.facinematograph.FacinematographApplication
import ru.kavyrshin.facinematograph.di.global.modules.ApplicationModule
import ru.kavyrshin.facinematograph.di.global.modules.DataModule
import ru.kavyrshin.facinematograph.di.global.modules.DatabaseModule
import ru.kavyrshin.facinematograph.di.global.modules.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, DataModule::class, NetworkModule::class, DatabaseModule::class))
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application : FacinematographApplication) : Builder

        fun build() : ApplicationComponent
    }


}