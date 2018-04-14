package ru.kavyrshin.facinematograph.presentation.views

import com.arellomobile.mvp.MvpView

interface BaseView : MvpView {
    fun showError(message : String)
    fun showLoading()
    fun hideLoading()
}