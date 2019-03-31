package com.cearleysoftware.byob.ui.fragments.customize

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.constants.MilkNames
import com.cearleysoftware.byob.extensions.inflateTo
import com.cearleysoftware.byob.models.MilksData
import com.cearleysoftware.byob.ui.adapters.MilksAdapter
import com.cearleysoftware.byob.ui.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.cearleysoftware.byob.extensions.getDimension
import com.cearleysoftware.byob.extensions.safeActivity
import kotlinx.android.synthetic.main.fragment_milks.*

//  Copyright © 2019 Cearley Software. All rights reserved.

class MilksFragment: Fragment() {

    private val mainViewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflateTo(R.layout.fragment_milks, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {

        val milkAdapter = MilksAdapter(getList())
        recyclerView.apply {
            layoutManager = LinearLayoutManager(safeActivity)
            adapter = milkAdapter
        }

        backButton.setOnClickListener { safeActivity.onBackPressed() }
        nextButton.setOnClickListener { mainViewModel.saveMilks(milkAdapter.list) }
    }

    private fun getList(): List<MilksData> {
        val customDrinkMilks = mainViewModel.customDrinkData.milks

        if (customDrinkMilks.isNotEmpty()){
            return customDrinkMilks.toList()
        }
        else {
            val array = resources.getStringArray(R.array.milks_array)
            val list = ArrayList<MilksData>()
            for (milk in array) {
                //todo: Move this to custom drink viewModel
                val milkData = MilksData(milk = milk)
                val milkName = milkData.milk
                when(milkName){
                    MilkNames.WHOLE -> addWholeMilkAttributes(milkData)
                    MilkNames.ALMOND -> addAlmondMilkAttributes(milkData)
                    MilkNames.COCONUT -> addCoconutMilkAttributes(milkData)
                    MilkNames.HALF_AND_HALF -> addHalfAndHalfAttributes(milkData)
                    MilkNames.HEAVY_CREAM -> addHeavyCreamAttributes(milkData)
                    MilkNames.NON_FAT -> addNonFatAttributes(milkData)
                    MilkNames.ONE_PERCENT -> addOnePercentAttributes(milkData)
                    MilkNames.SOY -> addSoyAttributes(milkData)
                    MilkNames.TWO_PERCENT -> addTwoPercentAttributes(milkData)
                }
                list.add(milkData)
            }
            return list
        }
    }

    private fun addTwoPercentAttributes(milkData: MilksData) {
        milkData.calories = getDimension(R.dimen.two_percent_calories)
        milkData.carbs = getDimension(R.dimen.two_percent_carbs)
        milkData.fats = getDimension(R.dimen.two_percent_fats)
        milkData.protein = getDimension(R.dimen.two_percent_protein)
        milkData.sugar = getDimension(R.dimen.two_percent_sugar)
    }

    private fun addSoyAttributes(milkData: MilksData) {
        milkData.calories = getDimension(R.dimen.soy_milk_calories)
        milkData.carbs = getDimension(R.dimen.soy_milk_carbs)
        milkData.fats = getDimension(R.dimen.soy_milk_fats)
        milkData.protein = getDimension(R.dimen.soy_milk_protein)
        milkData.sugar = getDimension(R.dimen.soy_milk_sugar)
    }

    private fun addOnePercentAttributes(milkData: MilksData) {
        milkData.calories = getDimension(R.dimen.one_percent_calories)
        milkData.carbs = getDimension(R.dimen.one_percent_carbs)
        milkData.fats = getDimension(R.dimen.one_percent_fats)
        milkData.protein = getDimension(R.dimen.one_percent_protein)
        milkData.sugar = getDimension(R.dimen.one_percent_sugar)
    }

    private fun addNonFatAttributes(milkData: MilksData) {
        milkData.calories = getDimension(R.dimen.non_fat_calories)
        milkData.carbs = getDimension(R.dimen.non_fat_carbs)
        milkData.fats = getDimension(R.dimen.non_fat_fats)
        milkData.protein = getDimension(R.dimen.non_fat_protein)
        milkData.sugar = getDimension(R.dimen.non_fat_sugar)
    }

    private fun addHeavyCreamAttributes(milkData: MilksData) {
        milkData.calories = getDimension(R.dimen.heavy_cream_calories)
        milkData.carbs = getDimension(R.dimen.heavy_cream_carbs)
        milkData.fats = getDimension(R.dimen.heavy_cream_fats)
        milkData.protein = getDimension(R.dimen.heavy_cream_protein)
        milkData.sugar = getDimension(R.dimen.heavy_cream_sugar)
    }

    private fun addHalfAndHalfAttributes(milkData: MilksData) {
        milkData.calories = getDimension(R.dimen.half_and_half_calories)
        milkData.carbs = getDimension(R.dimen.half_and_half_carbs)
        milkData.fats = getDimension(R.dimen.half_and_half_fats)
        milkData.protein = getDimension(R.dimen.half_and_half_protein)
        milkData.sugar = getDimension(R.dimen.half_and_half_sugar)
    }

    private fun addCoconutMilkAttributes(milkData: MilksData) {
        milkData.calories = getDimension(R.dimen.coconut_milk_calories)
        milkData.carbs = getDimension(R.dimen.coconut_milk_carbs)
        milkData.fats = getDimension(R.dimen.coconut_milk_fats)
        milkData.protein = getDimension(R.dimen.coconut_milk_protein)
        milkData.sugar = getDimension(R.dimen.coconut_milk_sugar)
    }

    private fun addAlmondMilkAttributes(milkData: MilksData) {
        milkData.calories = getDimension(R.dimen.almond_milk_calories)
        milkData.carbs = getDimension(R.dimen.almond_milk_carbs)
        milkData.fats = getDimension(R.dimen.almond_milk_fats)
        milkData.protein = getDimension(R.dimen.almond_milk_protein)
        milkData.sugar = getDimension(R.dimen.almond_milk_sugar)
    }

    private fun addWholeMilkAttributes(milkData: MilksData) {
        milkData.calories = getDimension(R.dimen.whole_milk_calories)
        milkData.carbs = getDimension(R.dimen.whole_milk_carbs)
        milkData.fats = getDimension(R.dimen.whole_milk_fats)
        milkData.protein = getDimension(R.dimen.whole_milk_protein)
        milkData.sugar = getDimension(R.dimen.whole_milk_sugar)
    }

}