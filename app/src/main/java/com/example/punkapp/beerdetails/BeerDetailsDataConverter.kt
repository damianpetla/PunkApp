package com.example.punkapp.beerdetails

import com.example.punkapp.api.Beer

class BeerDetailsDataConverter {

    fun convertDataToView(beer: Beer) : BeerDetails {
        val hops = beer.ingredients?.hops?.mapNotNull { it.name } ?: emptyList()
        val malts = beer.ingredients?.malt?.mapNotNull { it.name } ?: emptyList()
        val methods = mutableListOf<Method>()
        beer.method?.fermentation?.let {
            methods.add(Method("Fermentation", it.temp?.value))
        }
        beer.method?.mashTemp?.forEach {
            methods.add(Method("Mash", it.temp?.value))
        }
        beer.method?.twist?.let {
            methods.add(Method(it))
        }
        return BeerDetails(beer.imageUrl!!, beer.name!!, beer.abv!!, beer.description!!, hops, malts, methods)
    }
}