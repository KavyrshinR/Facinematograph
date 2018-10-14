package ru.kavyrshin.facinematograph.di.global.modules

import org.koin.dsl.module.module
import ru.kavyrshin.facinematograph.domain.interactors.FavouriteFilmsInteractor
import ru.kavyrshin.facinematograph.domain.interactors.SearchFilmsInteractor
import ru.kavyrshin.facinematograph.presentation.presenters.FavouriteFilmsPresenter
import ru.kavyrshin.facinematograph.presentation.presenters.SearchFilmsPresenter

val applicationModule = module {
    factory { FavouriteFilmsInteractor(get()) }
    factory { SearchFilmsInteractor(get()) }

    factory { FavouriteFilmsPresenter(get()) }
    factory { SearchFilmsPresenter(get()) }
}