package data

import data.dto.MoviesSearchRequest
import data.dto.MoviesSearchResponse
import domain.api.MoviesRepository
import domain.models.Movie
import util.Resource

class MoviesRepositoryImpl(private val networkClient: NetworkClient) : MoviesRepository {

    override fun findMovies(expression: String): Resource<List<Movie>> {

        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }

            200 -> {
                Resource.Success((response as MoviesSearchResponse).results.map {
                    Movie(it.id, it.resultType, it.image, it.title, it.description)})
            }

            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }
}