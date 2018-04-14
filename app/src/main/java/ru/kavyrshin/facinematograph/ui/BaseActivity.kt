package ru.kavyrshin.facinematograph.ui

import com.arellomobile.mvp.MvpAppCompatActivity
import ru.kavyrshin.facinematograph.FacinematographApplication


open class BaseActivity : MvpAppCompatActivity() {

    private var application : FacinematographApplication? = null
        get() {
            if (application == null) {
                application = getApplication() as? FacinematographApplication
            }
            return application
        }
}