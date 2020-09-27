package com.example.punkapp.beerdetails

data class BeerDetails(val imageUrl: String, val name: String, val abv: Double, val description: String, val hops: List<String>, val malts: List<String>, val methods: List<Method>)


data class Method(val name: String, val temp: Int? = null)