package presentation

import ui.poster.PosterView


class PosterPresenter(private val view: PosterView, private val imageUrl: String ) {

    fun onCreate() {

        view.showImage(imageUrl)
    }
}