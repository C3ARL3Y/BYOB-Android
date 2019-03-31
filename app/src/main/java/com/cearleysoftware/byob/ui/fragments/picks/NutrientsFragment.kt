package com.cearleysoftware.byob.ui.fragments.picks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.constants.Constants
import com.cearleysoftware.byob.extensions.inflateTo
import com.cearleysoftware.byob.extensions.safeActivity
import com.cearleysoftware.byob.models.Nutrients
import com.cearleysoftware.byob.ui.viewmodels.CreateDrinkViewModel
import com.cearleysoftware.byob.ui.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.nutrients_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.lang.NumberFormatException

//  Copyright © 2019 Cearley Software. All rights reserved.

class NutrientsFragment: Fragment() {

    private val mainViewModel by sharedViewModel<MainViewModel>()
    private val createDrinkViewModel by sharedViewModel<CreateDrinkViewModel>()

    private var nutrients: Nutrients? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nutrients = arguments?.getParcelable(Constants.NUTRIENTS)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflateTo(R.layout.nutrients_fragment, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        doneButton.setOnClickListener {
            storeNutrientsData()
        }
        loadNutrients(nutrients)
    }

    private fun storeNutrientsData() {
        nutrients?.caffeine = getIntFromText(caffeineEditText.text.toString())
        nutrients?.sugar = getIntFromText(sugarsEditText.text.toString())
        nutrients?.fats = getIntFromText(fatsEditText.text.toString())
        nutrients?.carbs = getIntFromText(carbsEditText.text.toString())
        nutrients?.protein = getIntFromText(proteinEditText.text.toString())
        nutrients?.calories = getIntFromText(caloriesEditText.text.toString())
        createDrinkViewModel.drinkData.nutrients = nutrients
        safeActivity.onBackPressed()
    }

    private fun getIntFromText(value: String): Int {
        var result = 0
        if (value.isNotBlank()){
            try {
                result = value.toInt()
            }
            catch (exception: NumberFormatException){
                exception.printStackTrace()
            }
        }

        return result
    }

    private fun loadNutrients(nutrients: Nutrients?) {
        nutrients?.let {
            caloriesEditText.setText(nutrients.calories.toString())
            proteinEditText.setText(nutrients.protein.toString())
            carbsEditText.setText(nutrients.carbs.toString())
            fatsEditText.setText(nutrients.fats.toString())
            sugarsEditText.setText(nutrients.sugar.toString())
            caffeineEditText.setText(nutrients.caffeine.toString())
        }
    }

    companion object {
        fun newInstance(nutrients: Nutrients?): NutrientsFragment{
            val bundle = bundleOf(Constants.NUTRIENTS to nutrients)
            val fragment = NutrientsFragment()
            fragment.arguments = bundle
            return fragment

        }
    }
}