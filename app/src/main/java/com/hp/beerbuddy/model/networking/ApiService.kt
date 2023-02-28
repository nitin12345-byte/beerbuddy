package com.hp.beerbuddy.model.networking

import com.hp.beerbuddy.constant.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build()
    }

    fun getPunkApiService(): PunkApiInterface {
        return retrofit.create(PunkApiInterface::class.java)
    }
}