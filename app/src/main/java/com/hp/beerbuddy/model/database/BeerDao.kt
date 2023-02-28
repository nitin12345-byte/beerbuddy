package com.hp.beerbuddy.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hp.beerbuddy.model.Beer

@Dao
interface BeerDao {
    @Query("Select * from beer")
    fun getBeers(): List<Beer>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(beers: List<Beer>)
}