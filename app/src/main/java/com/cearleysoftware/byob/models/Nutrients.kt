package com.cearleysoftware.byob.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//  Copyright © 2019 Cearley Software. All rights reserved.

@Parcelize
data class Nutrients(var id: Int = -1,
                     var calories: Int = 0,
                     var protein: Int = 0,
                     var carbs: Int = 0,
                     var fats: Int = 0,
                     var sugar: Int = 0,
                     var caffeine: Int = 0) : Parcelable