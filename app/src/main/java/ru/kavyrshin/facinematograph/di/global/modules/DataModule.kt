package ru.kavyrshin.facinematograph.di.global.modules

import org.koin.dsl.module.module
import ru.kavyrshin.facinematograph.data.repositories.FilmsRepository
import ru.kavyrshin.facinematograph.domain.global.repositories.IFilmsRepository


val dataModule = module {
    factory<IFilmsRepository> { FilmsRepository(get(), get()) }
}