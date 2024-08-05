package com.itproger.spr_15_clean_architecture_films.di

import domain.api.MoviesInteractor
import domain.impl.MoviesInteractorImpl
import org.koin.dsl.module

val interactorModule = module {

    single<MoviesInteractor> {
        MoviesInteractorImpl(get())
    }

//    single<SearchHistoryInteractor> { //no
//        SearchHistoryInteractorImpl(get())
//    }

}