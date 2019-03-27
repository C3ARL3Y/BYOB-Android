package com.cearleysoftware.byob.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cearleysoftware.byob.models.CustomDrink

@Dao
interface DrinkDao {

    @Query("SELECT * FROM custom_drinks")
    fun getAll(): List<CustomDrink>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDrink(drinks: CustomDrink)

}