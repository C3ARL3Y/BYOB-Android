package com.cearleysoftware.byob.models

//  Copyright © 2019 Cearley Software. All rights reserved.

data class CustomDrink(var base: String = "",
                       var milks: ArrayList<MilksData> = ArrayList(),
                       var syrups: ArrayList<SyrupsData> = ArrayList())
