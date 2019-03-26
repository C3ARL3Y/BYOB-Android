package com.cearleysoftware.byob.ui.bindings

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.cearleysoftware.byob.models.Nutrients

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