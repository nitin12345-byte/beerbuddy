package com.hp.beerbuddy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hp.beerbuddy.model.repositories.BeerRepository

class BeerViewModelProviderFactory(private val beerRepository: BeerRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BeerViewModel(beerRepository) as T
    }
}