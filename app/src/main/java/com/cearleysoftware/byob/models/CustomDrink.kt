package com.cearleysoftware.byob.models

data class CustomDrink(var base: String = "",
                       var milks: ArrayList<MilksData> = ArrayList(),
                       var syrups: ArrayList<SyrupsData> = ArrayList())
