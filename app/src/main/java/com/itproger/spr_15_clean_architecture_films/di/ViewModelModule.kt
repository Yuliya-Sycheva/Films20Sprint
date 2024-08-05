package com.itproger.spr_15_clean_architecture_films.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import presentation.movies.MoviesSearchViewModel

val viewModelModule = module {

    viewModel {
        MoviesSearchViewModel(get())
    }

//    viewModel {   //TODO
//        PosterViewModel()
//    }

}