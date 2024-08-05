package com.itproger.spr_15_clean_architecture_films.di

import android.content.Context
import data.network.IMDbApiService

import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import data.NetworkClient
import data.SearchHistoryStorage
import data.local.SharedPreferencesSearchHistoryStorage
import data.network.RetrofitNetworkClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.factory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single<IMDbApiService> {
        Retrofit.Builder()
            .baseUrl("https://tv-api.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IMDbApiService::class.java)
    }

//    single {
//        androidContext()
//            .getSharedPreferences("local_storage", Context.MODE_PRIVATE)
//    }
//
//    factory {
//        Gson()
//    }

//    single<SearchHistoryStorage> {
//        SharedPreferencesSearchHistoryStorage(get(), get())
//    }
//
    single<NetworkClient> {
         RetrofitNetworkClient(get(), androidContext())
    }
}