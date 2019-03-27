package com.cearleysoftware.byob.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cearleysoftware.byob.models.CustomDrink
import com.cearleysoftware.byob.models.MilksData
import com.cearleysoftware.byob.models.SyrupsData

@Database(entities = arrayOf(CustomDrink::class, MilksData::class, SyrupsData::class), version = 1)
abstract class BYOBDatabase: RoomDatabase() {

    abstract fun customDrinkDao(): DrinkDao

    abstract fun milksDao(): MilksDao

    abstract fun syrupsDao(): SyrupsDao

}