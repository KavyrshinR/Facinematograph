package ru.kavyrshin.facinematograph.presentation.presenters

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BasePresenter<View : MvpView> : MvpPresenter<View>() {

    private var compositeDisposable : CompositeDisposable? = CompositeDisposable()

    fun unsubscribeOnDestroy(disposable: Disposable) {
        compositeDisposable?.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.dispose()
    }
}