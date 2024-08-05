package data.network

import data.dto.MoviesSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call

interface IMDbApiService {
    @GET("/en/API/SearchMovie/k_zcuw1ytf/{expression}")
    fun findMovies(@Path("expression") expression: String): Call<MoviesSearchResponse>
}