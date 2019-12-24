package com.cearleysoftware.byob.util

import android.content.Context
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.constants.SyrupNames
import com.cearleysoftware.byob.extensions.getDimension
import com.cearleysoftware.byob.models.SyrupsData

object SyrupUtils{

    fun getSyrupList(context: Context, customDrinkSyrups: List<SyrupsData>): List<SyrupsData> {
        if (customDrinkSyrups.isNotEmpty()){
            return customDrinkSyrups.toList()
        }
        else {
            val array = context.resources.getStringArray(R.array.syrups_array)
            val list = ArrayList<SyrupsData>()
            for (syrup in array) {
                val syrupData = SyrupsData(syrup = syrup)
                when(syrupData.syrup){
                    SyrupNames.VANILLA -> addVanillaAttributes(context, syrupData)
                    SyrupNames.CARAMEL -> addCaramelAttributes(context, syrupData)
                    SyrupNames.HAZELNUT -> addHazelnutAttributes(context, syrupData)
                    SyrupNames.TOFFEE_NUT -> addToffeeAttributes(context, syrupData)
                    SyrupNames.PEPPERMINT -> addPeppermintAttributes(context, syrupData)
                    SyrupNames.RASPBERRY -> addRaspberryAttributes(context, syrupData)
                    SyrupNames.CLASSIC -> addClassicAttributes(context, syrupData)
                    SyrupNames.CASCARA -> addCascaraAttributes(context, syrupData)
                    SyrupNames.WHITE_MOCHA -> addWhiteMochaAttributes(context, syrupData)
                    SyrupNames.MOCHA -> addMochaAttributes(context, syrupData)
                    SyrupNames.CINNAMON_DOLCE -> addCinnamonDolceAttributes(context, syrupData)
                    SyrupNames.PUMPKIN_SPICE -> addPumpkinAttributes(context, syrupData)
                    SyrupNames.GINGERBREAD -> addGingerbreadAttributes(context, syrupData)
                    SyrupNames.CARAMEL_BRULEE -> addCaramelBruleeAttributes(context, syrupData)
                    SyrupNames.TOASTED_WHITE_MOCHA -> addToastedWhiteMochaAttributes(context, syrupData)

                }
                list.add(syrupData)
            }
            return list
        }
    }

    private fun addToastedWhiteMochaAttributes(context: Context, syrupData: SyrupsData) {
        syrupData.calories = context.getDimension(R.dimen.toasted_white_mocha_calories)
        syrupData.carbs = context.getDimension(R.dimen.toasted_white_mocha_carbs)
        syrupData.fats = context.getDimension(R.dimen.toasted_white_mocha_fats)
        syrupData.protein = context.getDimension(R.dimen.toasted_white_mocha_protein)
        syrupData.sugar = context.getDimension(R.dimen.toasted_white_mocha_sugar)
    }

    private fun addCaramelBruleeAttributes(context: Context, syrupData: SyrupsData) {
        syrupData.calories = context.getDimension(R.dimen.caramel_brulee_calories)
        syrupData.carbs = context.getDimension(R.dimen.caramel_brulee_carbs)
        syrupData.fats = context.getDimension(R.dimen.caramel_brulee_fats)
        syrupData.protein = context.getDimension(R.dimen.caramel_brulee_protein)
        syrupData.sugar = context.getDimension(R.dimen.caramel_brulee_sugar)
    }

    private fun addGingerbreadAttributes(context: Context, syrupData: SyrupsData) {
        syrupData.calories = context.getDimension(R.dimen.gingerbread_calories)
        syrupData.carbs = context.getDimension(R.dimen.gingerbread_carbs)
        syrupData.fats = context.getDimension(R.dimen.gingerbread_fats)
        syrupData.protein = context.getDimension(R.dimen.gingerbread_protein)
        syrupData.sugar = context.getDimension(R.dimen.gingerbread_sugar)
    }

    private fun addPumpkinAttributes(context: Context, syrupData: SyrupsData) {
        syrupData.calories = context.getDimension(R.dimen.pumpkin_spice_calories)
        syrupData.carbs = context.getDimension(R.dimen.pumpkin_spice_carbs)
        syrupData.fats = context.getDimension(R.dimen.pumpkin_spice_fats)
        syrupData.protein = context.getDimension(R.dimen.pumpkin_spice_protein)
        syrupData.sugar = context.getDimension(R.dimen.pumpkin_spice_sugar)
    }

    private fun addCinnamonDolceAttributes(context: Context, syrupData: SyrupsData) {
        syrupData.calories = context.getDimension(R.dimen.cinnamon_dolce_calories)
        syrupData.carbs = context.getDimension(R.dimen.cinnamon_dolce_carbs)
        syrupData.fats = context.getDimension(R.dimen.cinnamon_dolce_fats)
        syrupData.protein = context.getDimension(R.dimen.cinnamon_dolce_protein)
        syrupData.sugar = context.getDimension(R.dimen.cinnamon_dolce_sugar)
    }

    private fun addMochaAttributes(context: Context, syrupData: SyrupsData) {
        syrupData.calories = context.getDimension(R.dimen.mocha_calories)
        syrupData.carbs = context.getDimension(R.dimen.mocha_carbs)
        syrupData.fats = context.getDimension(R.dimen.mocha_fats)
        syrupData.protein = context.getDimension(R.dimen.mocha_protein)
        syrupData.sugar = context.getDimension(R.dimen.mocha_sugar)
    }

    private fun addWhiteMochaAttributes(context: Context, syrupData: SyrupsData) {
        syrupData.calories = context.getDimension(R.dimen.white_mocha_calories)
        syrupData.carbs = context.getDimension(R.dimen.white_mocha_carbs)
        syrupData.fats = context.getDimension(R.dimen.white_mocha_fats)
        syrupData.protein = context.getDimension(R.dimen.white_mocha_protein)
        syrupData.sugar = context.getDimension(R.dimen.white_mocha_sugar)
    }

    private fun addCascaraAttributes(context: Context, syrupData: SyrupsData) {
        syrupData.calories = context.getDimension(R.dimen.cascara_calories)
        syrupData.carbs = context.getDimension(R.dimen.cascara_carbs)
        syrupData.fats = context.getDimension(R.dimen.cascara_fats)
        syrupData.protein = context.getDimension(R.dimen.cascara_protein)
        syrupData.sugar = context.getDimension(R.dimen.cascara_sugar)
    }

    private fun addClassicAttributes(context: Context, syrupData: SyrupsData) {
        syrupData.calories = context.getDimension(R.dimen.classic_calories)
        syrupData.carbs = context.getDimension(R.dimen.classic_carbs)
        syrupData.fats = context.getDimension(R.dimen.classic_fats)
        syrupData.protein = context.getDimension(R.dimen.classic_protein)
        syrupData.sugar = context.getDimension(R.dimen.classic_sugar)
    }

    private fun addRaspberryAttributes(context: Context, syrupData: SyrupsData) {
        syrupData.calories = context.getDimension(R.dimen.raspberry_calories)
        syrupData.carbs = context.getDimension(R.dimen.raspberry_carbs)
        syrupData.fats = context.getDimension(R.dimen.raspberry_fats)
        syrupData.protein = context.getDimension(R.dimen.raspberry_protein)
        syrupData.sugar = context.getDimension(R.dimen.raspberry_sugar)
    }

    private fun addPeppermintAttributes(context: Context, syrupData: SyrupsData) {
        syrupData.calories = context.getDimension(R.dimen.peppermint_calories)
        syrupData.carbs = context.getDimension(R.dimen.peppermint_carbs)
        syrupData.fats = context.getDimension(R.dimen.peppermint_fats)
        syrupData.protein = context.getDimension(R.dimen.peppermint_protein)
        syrupData.sugar = context.getDimension(R.dimen.peppermint_sugar)
    }

    private fun addToffeeAttributes(context: Context, syrupData: SyrupsData) {
        syrupData.calories = context.getDimension(R.dimen.toffee_nut_calories)
        syrupData.carbs = context.getDimension(R.dimen.toffee_nut_carbs)
        syrupData.fats = context.getDimension(R.dimen.toffee_nut_fats)
        syrupData.protein = context.getDimension(R.dimen.toffee_nut_protein)
        syrupData.sugar = context.getDimension(R.dimen.toffee_nut_sugar)
    }

    private fun addHazelnutAttributes(context: Context, syrupData: SyrupsData) {
        syrupData.calories = context.getDimension(R.dimen.hazelnut_calories)
        syrupData.carbs = context.getDimension(R.dimen.hazelnut_carbs)
        syrupData.fats = context.getDimension(R.dimen.hazelnut_fats)
        syrupData.protein = context.getDimension(R.dimen.hazelnut_protein)
        syrupData.sugar = context.getDimension(R.dimen.hazelnut_sugar)
    }

    private fun addCaramelAttributes(context: Context, syrupData: SyrupsData) {
        syrupData.calories = context.getDimension(R.dimen.caramel_calories)
        syrupData.carbs = context.getDimension(R.dimen.caramel_carbs)
        syrupData.fats = context.getDimension(R.dimen.caramel_fats)
        syrupData.protein = context.getDimension(R.dimen.caramel_protein)
        syrupData.sugar = context.getDimension(R.dimen.caramel_sugar)
    }

    private fun addVanillaAttributes(context: Context, syrupData: SyrupsData) {
        syrupData.calories = context.getDimension(R.dimen.vanilla_calories)
        syrupData.carbs = context.getDimension(R.dimen.vanilla_carbs)
        syrupData.fats = context.getDimension(R.dimen.vanilla_fats)
        syrupData.protein = context.getDimension(R.dimen.vanilla_protein)
        syrupData.sugar = context.getDimension(R.dimen.vanilla_sugar)
    }
}