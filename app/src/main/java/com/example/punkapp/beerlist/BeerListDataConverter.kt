package com.example.punkapp.beerlist

import com.example.punkapp.api.Beer

class BeerListDataConverter {

    fun convertDataToView(beers: List<Beer>): List<BeerListItem> {
        return beers.filter { it.id != null && it.name != null && it.imageUrl != null && it.abv != null }
            .map { BeerListItem(it.id!!, it.name!!, it.imageUrl!!, it.abv!!) }
    }
}