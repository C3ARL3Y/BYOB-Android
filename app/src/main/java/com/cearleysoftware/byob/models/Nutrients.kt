package com.cearleysoftware.byob.models

import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

//  Copyright © 2019 Cearley Software. All rights reserved.

@Parcelize
@IgnoreExtraProperties
data class Nutrients(var id: Int,
                     var calories: Int = 0,
                     var protein: Int = 0,
                     var carbs: Int = 0,
                     var fats: Int = 0,
                     var sugar: Int = 0,
                     var caffeine: Int = 0) : Parcelable