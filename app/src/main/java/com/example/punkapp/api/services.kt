package com.example.punkapp.api

import retrofit2.Response
import retrofit2.http.GET

interface PunkApi {

    @GET("beers")
    suspend fun getBeers() : Response<List<Beer>>
}