package com.example.punkapp.beerdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.punkapp.data.BeerRepository

class BeerDetailsViewModel(
    private val beerId: Int,
    private val beerRepository: BeerRepository,
    private val converter: BeerDetailsDataConverter
) : ViewModel() {

    val state: LiveData<BeerDetails> = liveData {

        val beer = beerRepository.getBeer(beerId)
        emit(converter.convertDataToView(beer))
    }


}