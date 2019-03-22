package com.cearleysoftware.byob.ui.bindings

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.cearleysoftware.byob.models.Nutrients

@BindingAdapter("nutrients")
fun setNutrients(view: TextView, nutrients: Nutrients) {
    view.text = "Calories: ${nutrients.calories}\n\n" +
            "Protein: ${nutrients.protein}\n\n" +
            "Carbs: ${nutrients.carbs}\n\n" +
            "Fats: ${nutrients.fats}\n\n" +
            "Sugar: ${nutrients.sugar}\n\n" +
            "Caffeine: ${nutrients.caffeine}"
}

@BindingAdapter("steps")
fun setSteps(view: TextView, steps: List<String>) {
    var stepsString = ""
    for (step in steps){
        stepsString += step + "\n\n"
    }
    view.text = stepsString.trim()
}

@BindingAdapter("imageUrl")
fun setImageUrl(view: ImageView, imageUrl: String){
    Glide.with(view)
            .load(imageUrl)
            .into(view)
}