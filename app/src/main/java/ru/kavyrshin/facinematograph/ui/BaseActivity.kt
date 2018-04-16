package ru.kavyrshin.facinematograph.ui

import com.arellomobile.mvp.MvpAppCompatActivity
import ru.kavyrshin.facinematograph.FacinematographApplication


open class BaseActivity : MvpAppCompatActivity() {

    public var application : FacinematographApplication? = null
        private set
        get() {
            if (field == null) {
                field = getApplication() as? FacinematographApplication
            }
            return field
        }
}