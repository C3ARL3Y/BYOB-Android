package com.cearleysoftware.byob.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

//  Copyright © 2019 Cearley Software. All rights reserved.

@Parcelize
data class Drink(@get:Exclude var id: String = "",
                 var name: String = "",
                 var imageURL: String = "",
                 var description: String = "",
                 var nutrients: Nutrients = Nutrients(),
                 var steps: ArrayList<String> = ArrayList(),
                 var type: String = "",
                 var timestamp: Long = 0) : Parcelable