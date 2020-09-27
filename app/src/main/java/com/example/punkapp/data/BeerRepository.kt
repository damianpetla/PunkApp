package com.example.punkapp.data

import com.example.punkapp.api.Beer
import com.example.punkapp.api.PunkApi

class BeerRepository(val api: PunkApi) {

    private var storeLists: List<Beer> = emptyList()

    suspend fun getBeers(): List<Beer> {
        val response = api.getBeers()
        if (response.isSuccessful) {
            storeLists = response.body() ?: emptyList()
            return storeLists
        }
        throw BeerDataException("Failed fetching beers from the API. Responded with code ${response.code()}")
    }

    suspend fun getBeer(id: Int): Beer {
        return storeLists.first { it.id == id }
    }
}