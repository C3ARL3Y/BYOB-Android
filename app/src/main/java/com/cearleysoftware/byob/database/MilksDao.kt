package com.cearleysoftware.byob.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cearleysoftware.byob.models.MilksData

@Dao
interface MilksDao {

    @Query("SELECT * FROM milks")
    fun getAll(): List<MilksData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMilk(milk: MilksData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAllMilks(milks: List<MilksData>)

    @Query("SELECT * FROM milks WHERE drink_id=:drinkId")
    fun getAllForDrink(drinkId: String): List<MilksData>
}