package com.cearleysoftware.byob.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

//  Copyright © 2019 Cearley Software. All rights reserved.

@Parcelize
@Entity(tableName = "milks",
        foreignKeys = arrayOf(ForeignKey(
        entity = CustomDrink::class,
        parentColumns = arrayOf("drink_id"),
        childColumns = arrayOf("drink_id"),
        onDelete = ForeignKey.CASCADE)))

data class MilksData(@PrimaryKey(autoGenerate = true)
                     @ColumnInfo(name = "id") var milkId: Int = 0,
                     @ColumnInfo(name = "drink_id")var drinkId: String = "",
                     var milk: String,
                     var calories: Double = 0.0,
                     var protein: Double = 0.0,
                     var carbs: Double = 0.0,
                     var fats: Double = 0.0,
                     var sugar: Double = 0.0,
                     var count: Int = 0) : Parcelable
