package com.cearleysoftware.byob.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cearleysoftware.byob.models.SyrupsData

@Dao
interface SyrupsDao {

    @Query("SELECT * FROM syrups")
    fun getAll(): List<SyrupsData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSyrup(syrup: SyrupsData)

    @Query("SELECT * FROM syrups WHERE drink_id=:drinkId")
    fun getAllForDrink(drinkId: String): List<SyrupsData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAllSyrups(syrups: List<SyrupsData>)
}