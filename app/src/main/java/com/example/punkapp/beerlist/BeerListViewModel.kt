package com.example.punkapp.beerlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.punkapp.data.BeerRepository

class BeerListViewModel(
    private val beerRepository: BeerRepository,
    private val converter: BeerListDataConverter
) : ViewModel() {

    val state: LiveData<BeerListState> = liveData {
        emit(BeerListState.loading())

        try {
            val beers = beerRepository.getBeers()
            val beerViewItems = converter.convertDataToView(beers)
            emit(BeerListState.loaded(beerViewItems))
        } catch (ex: Exception) {
            emit(BeerListState.error())
        }
    }

}