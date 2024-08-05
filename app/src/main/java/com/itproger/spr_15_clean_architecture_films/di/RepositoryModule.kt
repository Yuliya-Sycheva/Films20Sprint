package com.itproger.spr_15_clean_architecture_films.di

import data.MoviesRepositoryImpl
import domain.api.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<MoviesRepository> {
        MoviesRepositoryImpl(get())
    }

}