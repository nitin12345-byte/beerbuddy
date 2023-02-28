package com.hp.beerbuddy.model.networking

import com.hp.beerbuddy.model.Beer
import retrofit2.Response
import retrofit2.http.GET

interface PunkApiInterface {
    @GET("/v2/beers")
    suspend fun getBeers(): Response<List<Beer>>
}