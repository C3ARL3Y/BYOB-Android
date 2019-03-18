package com.cearleysoftware.byob.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Drink(var id: Int,
                 var name: String,
                 var image: String? = null,
                 var description: String,
                 var nutrients: Nutrients,
                 var steps: List<String>,
                 var type: String)