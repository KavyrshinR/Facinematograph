package ru.kavyrshin.facinematograph.di

import dagger.Subcomponent
import ru.kavyrshin.facinematograph.presentation.presenters.FavouriteFilmsPresenter

@Subcomponent
interface FavouriteFilmsComponent {

    @Subcomponent.Builder
    interface Builder {

        fun build(): FavouriteFilmsComponent
    }

    fun presenter(): FavouriteFilmsPresenter
}