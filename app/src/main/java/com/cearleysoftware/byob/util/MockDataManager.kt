package com.cearleysoftware.byob.util

import com.cearleysoftware.byob.models.Drink
import com.cearleysoftware.byob.models.Nutrients

object MockDataManager {

    fun getMockDrinks(): List<Drink>{
        val list = ArrayList<Drink>()
        val nutrients = Nutrients(0)
        val steps = ArrayList<String>()
        steps.add("first")
        steps.add("second")
        steps.add("third")
        for (i in 0 until 19){
            val drink = Drink("abc",
                    "drink $i",
                    "https://fetch-test2.s3.us-west-2.amazonaws.com/a22cdda0-3600-11e9-b713-9d2bb708b98d.jpeg",
                    "description $i",
                    nutrients,
                    steps,
                    "type: $i")
            list.add(drink)
        }
        return list
    }
}