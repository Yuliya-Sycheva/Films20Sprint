package util

import android.content.Context
import data.MoviesRepositoryImpl
import data.network.RetrofitNetworkClient
import domain.api.MoviesInteractor
import domain.api.MoviesRepository
import domain.impl.MoviesInteractorImpl
import presentation.movies.MoviesSearchViewModel
import presentation.PosterPresenter
import ui.poster.PosterView

object Creator {
//    private fun getMoviesRepository(context: Context): MoviesRepository {
//        return MoviesRepositoryImpl(RetrofitNetworkClient(context))
//    }

//    fun provideMoviesInteractor(context: Context): MoviesInteractor {
//        return MoviesInteractorImpl(getMoviesRepository(context))
//    }

    fun checkup() {

    }
    fun providePosterPresenter(posterView: PosterView, url: String): PosterPresenter {
        return PosterPresenter(posterView, url)
    }
}