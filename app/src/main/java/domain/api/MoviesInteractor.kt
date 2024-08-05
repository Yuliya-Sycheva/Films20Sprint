package domain.api

import domain.models.Movie

interface MoviesInteractor {
    fun findMovies(expression: String, consumer: MoviesConsumer)

    interface MoviesConsumer {
        fun consume(foundMovies: List<Movie>?, errorMessage : String?)
    }
}