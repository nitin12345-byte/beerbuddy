package com.hp.beerbuddy.model.repositories

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hp.beerbuddy.model.Beer
import com.hp.beerbuddy.model.database.AppDatabase
import com.hp.beerbuddy.model.database.BeerDao
import com.hp.beerbuddy.model.networking.ApiService
import com.hp.beerbuddy.model.networking.NetworkResult
import java.net.InetAddress


class BeerRepository(private val context: Context) {
    private val beersLiveData = MutableLiveData<NetworkResult<List<Beer>>>()

    private val beerDao: BeerDao by lazy {
        AppDatabase.getInstance(context).getBeerDao()
    }

    fun getBeerLiveData(): LiveData<NetworkResult<List<Beer>>> {
        return beersLiveData
    }

    suspend fun getBeers() {
        beersLiveData.postValue(NetworkResult.Loading())
        try {
            if (isInternetAvailable()) {
                val response = ApiService.getPunkApiService().getBeers()
                if (response.code() == 200) {
                    beersLiveData.postValue(NetworkResult.Success(response.body()!!))
                    beerDao.save(response.body()!!)
                } else {
                    Log.e(TAG, response.message())
                    beersLiveData.postValue(NetworkResult.Failure(response.message()))
                }
            } else {
                Log.d(TAG, beerDao.getBeers().size.toString())
                val beers = beerDao.getBeers()
                if (beers.isEmpty())
                    beersLiveData.postValue(NetworkResult.Failure(""))
                else
                    beersLiveData.postValue(NetworkResult.Success(beers))
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
            beersLiveData.postValue(NetworkResult.Failure(exception.message))
        }
    }

    private fun isInternetAvailable(): Boolean {
        return try {
            val ipAddr: InetAddress = InetAddress.getByName("www.google.com")
            !ipAddr.equals("")
        } catch (e: java.lang.Exception) {
            false
        }
    }

    companion object {
        private val TAG = BeerRepository::class.simpleName
    }
}