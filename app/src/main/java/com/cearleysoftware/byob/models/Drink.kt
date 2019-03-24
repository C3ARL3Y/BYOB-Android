package com.cearleysoftware.byob.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//  Copyright © 2019 Cearley Software. All rights reserved.

@Parcelize
data class Drink(var id: String = "",
                 var name: String = "",
                 var image: String = "",
                 var description: String = "",
                 var nutrients: Nutrients? = null,
                 var steps: List<String> = emptyList(),
                 var type: String = "") : Parcelable