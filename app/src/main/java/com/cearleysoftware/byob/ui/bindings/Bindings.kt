package com.cearleysoftware.byob.ui.bindings

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.cearleysoftware.byob.models.CustomDrink
import com.cearleysoftware.byob.models.MilksData
import com.cearleysoftware.byob.models.Nutrients
import com.cearleysoftware.byob.models.SyrupsData

//  Copyright © 2019 Cearley Software. All rights reserved.

@BindingAdapter("nutrients")
fun setNutrients(view: TextView, nutrients: Nutrients?) {
    nutrients?: return
    val nutrientsString = "Calories: ${nutrients.calories}\n\n" +
            "Protein: ${nutrients.protein}\n\n" +
            "Carbs: ${nutrients.carbs}\n\n" +
            "Fats: ${nutrients.fats}\n\n" +
            "Sugar: ${nutrients.sugar}\n\n" +
            "Caffeine: ${nutrients.caffeine}"
    view.text = nutrientsString
}

@BindingAdapter("steps")
fun setSteps(view: TextView, steps: List<String>) {
    var stepsString = ""
    steps.forEachIndexed { index, step ->
        stepsString += "Step ${index + 1}: $step\n\n"
    }
    view.text = stepsString.trim()
}

@BindingAdapter("imageUrl")
fun setImageUrl(view: ImageView, imageUrl: String?){
    Glide.with(view)
            .load(imageUrl)
            .into(view)
}

@BindingAdapter("milkAmount")
fun setMilkAmount(view: TextView, amount: Int){
    when(amount){
        0 -> view.text = "Empty"
        1 -> view.text = "Light"
        2 -> view.text = "Regular"
        3 -> view.text = "Extra"
        else -> view.text = "Extra"
    }
}

@BindingAdapter("syrupAmount")
fun setSyrupAmount(view: TextView, amount: Int){
    when(amount){
        0 -> view.text = "Empty"

        else -> view.text = "$amount"
    }
}

@BindingAdapter("setMilks")
fun setMilks(view: TextView, milks: List<MilksData>){
    var text = ""
    for (milk in milks){
        if (milk.count > 0){
            text += "- ${milk.milk}: ${milk.count}\n"
        }
    }
    view.text = text.trim()
}

@BindingAdapter("setSyrups")
fun setSyrups(view: TextView, syrups: List<SyrupsData>){
    var text = ""
    for (syrup in syrups){
        if (syrup.count > 0){
            text += "- ${syrup.syrup}: ${syrup.count}\n"
        }
    }
    view.text = text.trim()
}

@BindingAdapter("setNutritionFacts")
fun setNutirionFacts(view: TextView, drink: CustomDrink){
    var calories = 0.00
    var protein = 0.00
    var carbs = 0.00
    var sugar = 0.00

    val milks = drink.milks
    val syrups = drink.syrups
    for (milk in milks){
        val milkCount = milk.count
        calories += milk.calories * milkCount
        protein += milk.protein * milkCount
        carbs += milk.carbs * milkCount
        sugar += milk.sugar * milkCount
    }

    for(syrup in syrups){
        val syrupCount = syrup.count
        calories += syrup.calories * syrupCount
        protein += syrup.protein * syrupCount
        carbs += syrup.carbs * syrupCount
        sugar += syrup.sugar * syrupCount
    }
    val text = "Calories: $calories\n" +
            "Protein: $protein\n" +
            "Carbs: $carbs\n" +
            "Sugar: $sugar"
    view.text = text.trim()
}