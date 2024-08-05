package ui.movies

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import util.Creator
import com.itproger.spr_15_clean_architecture_films.R
import presentation.PosterPresenter
import ui.poster.PosterView

class PosterActivity : Activity(), PosterView {

    private lateinit var posterPresenter: PosterPresenter
    private lateinit var poster: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Мы не можем создать PosterPresenter раньше,
        // потому что нам нужен imageUrl, который
        // станет доступен только после super.onCreate

         val imageUrl: String = intent.extras?.getString("poster", "") ?:""

        posterPresenter = Creator.providePosterPresenter(this, imageUrl)

        setContentView(R.layout.activity_poster)
        poster = findViewById(R.id.poster)

        posterPresenter.onCreate()
    }

    override fun showImage(url: String) {
        Glide.with(applicationContext)
            .load(url)
            .into(poster)
    }
}