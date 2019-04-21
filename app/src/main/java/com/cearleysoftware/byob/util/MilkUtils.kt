package com.cearleysoftware.byob.util

import android.content.Context
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.constants.MilkNames
import com.cearleysoftware.byob.extensions.getDimension
import com.cearleysoftware.byob.models.MilksData

object MilkUtils {

    fun getMilksData(context: Context, customDrinkMilks: List<MilksData>): List<MilksData> {

        if (customDrinkMilks.isNotEmpty()){
            return customDrinkMilks.toList()
        }
        else {
            val array = context.resources.getStringArray(R.array.milks_array)
            val list = ArrayList<MilksData>()
            for (milk in array) {
                val milkData = MilksData(milk = milk)
                val milkName = milkData.milk
                when(milkName){
                    MilkNames.WHOLE -> addWholeMilkAttributes(context, milkData)
                    MilkNames.ALMOND -> addAlmondMilkAttributes(context, milkData)
                    MilkNames.COCONUT -> addCoconutMilkAttributes(context, milkData)
                    MilkNames.HALF_AND_HALF -> addHalfAndHalfAttributes(context, milkData)
                    MilkNames.HEAVY_CREAM -> addHeavyCreamAttributes(context, milkData)
                    MilkNames.NON_FAT -> addNonFatAttributes(context, milkData)
                    MilkNames.ONE_PERCENT -> addOnePercentAttributes(context, milkData)
                    MilkNames.SOY -> addSoyAttributes(context, milkData)
                    MilkNames.TWO_PERCENT -> addTwoPercentAttributes(context, milkData)
                }
                list.add(milkData)
            }
            return list
        }
    }

    private fun addTwoPercentAttributes(context: Context, milkData: MilksData) {
        milkData.calories = context.getDimension(R.dimen.two_percent_calories)
        milkData.carbs = context.getDimension(R.dimen.two_percent_carbs)
        milkData.fats = context.getDimension(R.dimen.two_percent_fats)
        milkData.protein = context.getDimension(R.dimen.two_percent_protein)
        milkData.sugar = context.getDimension(R.dimen.two_percent_sugar)
    }

    private fun addSoyAttributes(context: Context, milkData: MilksData) {
        milkData.calories = context.getDimension(R.dimen.soy_milk_calories)
        milkData.carbs = context.getDimension(R.dimen.soy_milk_carbs)
        milkData.fats = context.getDimension(R.dimen.soy_milk_fats)
        milkData.protein = context.getDimension(R.dimen.soy_milk_protein)
        milkData.sugar = context.getDimension(R.dimen.soy_milk_sugar)
    }

    private fun addOnePercentAttributes(context: Context, milkData: MilksData) {
        milkData.calories = context.getDimension(R.dimen.one_percent_calories)
        milkData.carbs = context.getDimension(R.dimen.one_percent_carbs)
        milkData.fats = context.getDimension(R.dimen.one_percent_fats)
        milkData.protein = context.getDimension(R.dimen.one_percent_protein)
        milkData.sugar = context.getDimension(R.dimen.one_percent_sugar)
    }

    private fun addNonFatAttributes(context: Context, milkData: MilksData) {
        milkData.calories = context.getDimension(R.dimen.non_fat_calories)
        milkData.carbs = context.getDimension(R.dimen.non_fat_carbs)
        milkData.fats = context.getDimension(R.dimen.non_fat_fats)
        milkData.protein = context.getDimension(R.dimen.non_fat_protein)
        milkData.sugar = context.getDimension(R.dimen.non_fat_sugar)
    }

    private fun addHeavyCreamAttributes(context: Context, milkData: MilksData) {
        milkData.calories = context.getDimension(R.dimen.heavy_cream_calories)
        milkData.carbs = context.getDimension(R.dimen.heavy_cream_carbs)
        milkData.fats = context.getDimension(R.dimen.heavy_cream_fats)
        milkData.protein = context.getDimension(R.dimen.heavy_cream_protein)
        milkData.sugar = context.getDimension(R.dimen.heavy_cream_sugar)
    }

    private fun addHalfAndHalfAttributes(context: Context, milkData: MilksData) {
        milkData.calories = context.getDimension(R.dimen.half_and_half_calories)
        milkData.carbs = context.getDimension(R.dimen.half_and_half_carbs)
        milkData.fats = context.getDimension(R.dimen.half_and_half_fats)
        milkData.protein = context.getDimension(R.dimen.half_and_half_protein)
        milkData.sugar = context.getDimension(R.dimen.half_and_half_sugar)
    }

    private fun addCoconutMilkAttributes(context: Context, milkData: MilksData) {
        milkData.calories = context.getDimension(R.dimen.coconut_milk_calories)
        milkData.carbs = context.getDimension(R.dimen.coconut_milk_carbs)
        milkData.fats = context.getDimension(R.dimen.coconut_milk_fats)
        milkData.protein = context.getDimension(R.dimen.coconut_milk_protein)
        milkData.sugar = context.getDimension(R.dimen.coconut_milk_sugar)
    }

    private fun addAlmondMilkAttributes(context: Context, milkData: MilksData) {
        milkData.calories = context.getDimension(R.dimen.almond_milk_calories)
        milkData.carbs = context.getDimension(R.dimen.almond_milk_carbs)
        milkData.fats = context.getDimension(R.dimen.almond_milk_fats)
        milkData.protein = context.getDimension(R.dimen.almond_milk_protein)
        milkData.sugar = context.getDimension(R.dimen.almond_milk_sugar)
    }

    private fun addWholeMilkAttributes(context: Context, milkData: MilksData) {
        milkData.calories = context.getDimension(R.dimen.whole_milk_calories)
        milkData.carbs = context.getDimension(R.dimen.whole_milk_carbs)
        milkData.fats = context.getDimension(R.dimen.whole_milk_fats)
        milkData.protein = context.getDimension(R.dimen.whole_milk_protein)
        milkData.sugar = context.getDimension(R.dimen.whole_milk_sugar)
    }
}