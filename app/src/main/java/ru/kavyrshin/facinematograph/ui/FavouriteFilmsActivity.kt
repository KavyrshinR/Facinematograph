package ru.kavyrshin.facinematograph.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import ru.kavyrshin.facinematograph.R

class FavouriteFilmsActivity : BaseActivity(), View.OnClickListener {

    private var btnSearch: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite_films)

        btnSearch = findViewById(R.id.btnSearch) as? Button
        btnSearch?.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        startActivity(Intent(this, SearchFilmsActivity::class.java))
    }

}