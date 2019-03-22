package com.cearleysoftware.byob.models

import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

//  Copyright © 2019 Cearley Software. All rights reserved.

@IgnoreExtraProperties
@Parcelize
data class Drink(var id: Int,
                 var name: String,
                 var image: String? = null,
                 var description: String,
                 var nutrients: Nutrients,
                 var steps: List<String>,
                 var type: String) : Parcelable