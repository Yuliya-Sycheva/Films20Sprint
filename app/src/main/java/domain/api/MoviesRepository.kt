package domain.api

import domain.models.Movie
import util.Resource

interface MoviesRepository {
    fun findMovies(expression: String): Resource<List<Movie>>
}