package com.cearleysoftware.byob.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

//  Copyright © 2019 Cearley Software. All rights reserved.

@Parcelize
@Entity(tableName = "custom_drinks")
data class CustomDrink(@PrimaryKey(autoGenerate = false)
                       @ColumnInfo(name = "drink_id")
                       var drinkId: String = UUID.randomUUID().toString(),
                       var base: String = "",
                       @Ignore var milks: ArrayList<MilksData> = ArrayList(),
                       @Ignore var syrups: ArrayList<SyrupsData> = ArrayList(),
                       var extra: String = "",
                       var name: String = "Unknown Name") : Parcelable {

    fun clear(){
        base = ""
        milks.clear()
        syrups.clear()
    }
}
