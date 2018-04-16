package ru.kavyrshin.facinematograph.di

import dagger.Subcomponent
import ru.kavyrshin.facinematograph.presentation.presenters.SearchFilmsPresenter

@Subcomponent
interface SearchFilmsComponent {

    @Subcomponent.Builder
    interface Builder {

        fun build(): SearchFilmsComponent
    }

    fun presenter(): SearchFilmsPresenter
}