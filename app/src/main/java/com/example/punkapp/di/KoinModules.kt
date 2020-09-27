package com.example.punkapp.di

import com.example.punkapp.api.PunkApi
import com.example.punkapp.beerdetails.BeerDetailsDataConverter
import com.example.punkapp.beerdetails.BeerDetailsViewModel
import com.example.punkapp.beerlist.BeerListDataConverter
import com.example.punkapp.beerlist.BeerListViewModel
import com.example.punkapp.data.BeerRepository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KoinModules {

    val appModule = module {

        single {
            Retrofit.Builder()
                .baseUrl("https://api.punkapi.com/v2/")
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(PunkApi::class.java)
        }

        single { BeerRepository(get()) }

        factory { BeerListDataConverter() }
        factory { BeerDetailsDataConverter() }

        viewModel { BeerListViewModel(get(), get()) }

        viewModel { (beerId: Int) -> BeerDetailsViewModel(beerId, get(), get()) }
    }
}