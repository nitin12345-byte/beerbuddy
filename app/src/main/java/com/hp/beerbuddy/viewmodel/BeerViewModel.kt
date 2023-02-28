package com.hp.beerbuddy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hp.beerbuddy.model.Beer
import com.hp.beerbuddy.model.networking.NetworkResult
import com.hp.beerbuddy.model.repositories.BeerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BeerViewModel(private val beerRepository: BeerRepository) : ViewModel() {
    private lateinit var selectedBeer: Beer

    fun getBeerLiveData(): LiveData<NetworkResult<List<Beer>>> {
        return beerRepository.getBeerLiveData()
    }

    fun getBeersList() {
        viewModelScope.launch(Dispatchers.IO) {
            beerRepository.getBeers()
        }
    }

    fun getSelectedBeer(): Beer {
        return selectedBeer
    }

    fun setSelectedBeer(selectedBeer: Beer) {
        this.selectedBeer = selectedBeer
    }
}