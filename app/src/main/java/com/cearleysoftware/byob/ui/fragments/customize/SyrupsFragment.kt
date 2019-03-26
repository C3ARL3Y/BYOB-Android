package com.cearleysoftware.byob.ui.fragments.customize

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.constants.SyrupNames
import com.cearleysoftware.byob.extensions.getDimension
import com.cearleysoftware.byob.extensions.inflateTo
import com.cearleysoftware.byob.extensions.safeActivity
import com.cearleysoftware.byob.models.SyrupsData
import com.cearleysoftware.byob.ui.adapters.SyrupsAdapter
import com.cearleysoftware.byob.ui.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.fragment_syrups.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

//  Copyright © 2019 Cearley Software. All rights reserved.

class SyrupsFragment: Fragment() {

    private val mainViewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflateTo(R.layout.fragment_syrups, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {

        val syrupAdapter = SyrupsAdapter(getList())
        recyclerView.apply {
            layoutManager = LinearLayoutManager(safeActivity)
            adapter = syrupAdapter
        }

        backButton.setOnClickListener { mainViewModel.popBackStack() }
        nextButton.setOnClickListener { mainViewModel.saveSyrups(syrupAdapter.list) }
    }

    private fun getList(): List<SyrupsData> {
        val customDrinkSyrups = mainViewModel.customDrinkData.syrups

        if (customDrinkSyrups.isNotEmpty()){
            return customDrinkSyrups.toList()
        }
        else {
            val array = resources.getStringArray(R.array.syrups_array)
            val list = ArrayList<SyrupsData>()
            for (syrup in array) {
                val syrupData = SyrupsData(syrup)
                val syrupName = syrupData.syrup
                when(syrupName){
                    SyrupNames.VANILLA -> addVanillaAttributes(syrupData)
                    SyrupNames.CARAMEL -> addCaramelAttributes(syrupData)
                    SyrupNames.HAZELNUT -> addHazelnutAttributes(syrupData)
                    SyrupNames.TOFFEE_NUT -> addToffeeAttributes(syrupData)
                    SyrupNames.PEPPERMINT -> addPeppermintAttributes(syrupData)
                    SyrupNames.RASPBERRY -> addRaspberryAttributes(syrupData)
                    SyrupNames.CLASSIC -> addClassicAttributes(syrupData)
                    SyrupNames.CASCARA -> addCascaraAttributes(syrupData)
                    SyrupNames.WHITE_MOCHA -> addWhiteMochaAttributes(syrupData)
                    SyrupNames.MOCHA -> addMochaAttributes(syrupData)
                    SyrupNames.CINNAMON_DOLCE -> addCinnamonDolceAttributes(syrupData)
                    SyrupNames.PUMPKIN_SPICE -> addPumpkinAttributes(syrupData)
                    SyrupNames.GINGERBREAD -> addGingerbreadAttributes(syrupData)
                    SyrupNames.CARAMEL_BRULEE -> addCaramelBruleeAttributes(syrupData)
                    SyrupNames.TOASTED_WHITE_MOCHA -> addToastedWhiteMochaAttributes(syrupData)

                }
                list.add(syrupData)
            }
            return list
        }
    }

    private fun addToastedWhiteMochaAttributes(syrupData: SyrupsData) {
        syrupData.calories = getDimension(R.dimen.toasted_white_mocha_calories)
        syrupData.carbs = getDimension(R.dimen.toasted_white_mocha_carbs)
        syrupData.fats = getDimension(R.dimen.toasted_white_mocha_fats)
        syrupData.protein = getDimension(R.dimen.toasted_white_mocha_protein)
        syrupData.sugar = getDimension(R.dimen.toasted_white_mocha_sugar)
    }

    private fun addCaramelBruleeAttributes(syrupData: SyrupsData) {
        syrupData.calories = getDimension(R.dimen.caramel_brulee_calories)
        syrupData.carbs = getDimension(R.dimen.caramel_brulee_carbs)
        syrupData.fats = getDimension(R.dimen.caramel_brulee_fats)
        syrupData.protein = getDimension(R.dimen.caramel_brulee_protein)
        syrupData.sugar = getDimension(R.dimen.caramel_brulee_sugar)
    }

    private fun addGingerbreadAttributes(syrupData: SyrupsData) {
        syrupData.calories = getDimension(R.dimen.gingerbread_calories)
        syrupData.carbs = getDimension(R.dimen.gingerbread_carbs)
        syrupData.fats = getDimension(R.dimen.gingerbread_fats)
        syrupData.protein = getDimension(R.dimen.gingerbread_protein)
        syrupData.sugar = getDimension(R.dimen.gingerbread_sugar)
    }

    private fun addPumpkinAttributes(syrupData: SyrupsData) {
        syrupData.calories = getDimension(R.dimen.pumpkin_spice_calories)
        syrupData.carbs = getDimension(R.dimen.pumpkin_spice_carbs)
        syrupData.fats = getDimension(R.dimen.pumpkin_spice_fats)
        syrupData.protein = getDimension(R.dimen.pumpkin_spice_protein)
        syrupData.sugar = getDimension(R.dimen.pumpkin_spice_sugar)
    }

    private fun addCinnamonDolceAttributes(syrupData: SyrupsData) {
        syrupData.calories = getDimension(R.dimen.cinnamon_dolce_calories)
        syrupData.carbs = getDimension(R.dimen.cinnamon_dolce_carbs)
        syrupData.fats = getDimension(R.dimen.cinnamon_dolce_fats)
        syrupData.protein = getDimension(R.dimen.cinnamon_dolce_protein)
        syrupData.sugar = getDimension(R.dimen.cinnamon_dolce_sugar)
    }

    private fun addMochaAttributes(syrupData: SyrupsData) {
        syrupData.calories = getDimension(R.dimen.mocha_calories)
        syrupData.carbs = getDimension(R.dimen.mocha_carbs)
        syrupData.fats = getDimension(R.dimen.mocha_fats)
        syrupData.protein = getDimension(R.dimen.mocha_protein)
        syrupData.sugar = getDimension(R.dimen.mocha_sugar)
    }

    private fun addWhiteMochaAttributes(syrupData: SyrupsData) {
        syrupData.calories = getDimension(R.dimen.white_mocha_calories)
        syrupData.carbs = getDimension(R.dimen.white_mocha_carbs)
        syrupData.fats = getDimension(R.dimen.white_mocha_fats)
        syrupData.protein = getDimension(R.dimen.white_mocha_protein)
        syrupData.sugar = getDimension(R.dimen.white_mocha_sugar)
    }

    private fun addCascaraAttributes(syrupData: SyrupsData) {
        syrupData.calories = getDimension(R.dimen.cascara_calories)
        syrupData.carbs = getDimension(R.dimen.cascara_carbs)
        syrupData.fats = getDimension(R.dimen.cascara_fats)
        syrupData.protein = getDimension(R.dimen.cascara_protein)
        syrupData.sugar = getDimension(R.dimen.cascara_sugar)
    }

    private fun addClassicAttributes(syrupData: SyrupsData) {
        syrupData.calories = getDimension(R.dimen.classic_calories)
        syrupData.carbs = getDimension(R.dimen.classic_carbs)
        syrupData.fats = getDimension(R.dimen.classic_fats)
        syrupData.protein = getDimension(R.dimen.classic_protein)
        syrupData.sugar = getDimension(R.dimen.classic_sugar)
    }

    private fun addRaspberryAttributes(syrupData: SyrupsData) {
        syrupData.calories = getDimension(R.dimen.raspberry_calories)
        syrupData.carbs = getDimension(R.dimen.raspberry_carbs)
        syrupData.fats = getDimension(R.dimen.raspberry_fats)
        syrupData.protein = getDimension(R.dimen.raspberry_protein)
        syrupData.sugar = getDimension(R.dimen.raspberry_sugar)
    }

    private fun addPeppermintAttributes(syrupData: SyrupsData) {
        syrupData.calories = getDimension(R.dimen.peppermint_calories)
        syrupData.carbs = getDimension(R.dimen.peppermint_carbs)
        syrupData.fats = getDimension(R.dimen.peppermint_fats)
        syrupData.protein = getDimension(R.dimen.peppermint_protein)
        syrupData.sugar = getDimension(R.dimen.peppermint_sugar)
    }

    private fun addToffeeAttributes(syrupData: SyrupsData) {
        syrupData.calories = getDimension(R.dimen.toffee_nut_calories)
        syrupData.carbs = getDimension(R.dimen.toffee_nut_carbs)
        syrupData.fats = getDimension(R.dimen.toffee_nut_fats)
        syrupData.protein = getDimension(R.dimen.toffee_nut_protein)
        syrupData.sugar = getDimension(R.dimen.toffee_nut_sugar)
    }

    private fun addHazelnutAttributes(syrupData: SyrupsData) {
        syrupData.calories = getDimension(R.dimen.hazelnut_calories)
        syrupData.carbs = getDimension(R.dimen.hazelnut_carbs)
        syrupData.fats = getDimension(R.dimen.hazelnut_fats)
        syrupData.protein = getDimension(R.dimen.hazelnut_protein)
        syrupData.sugar = getDimension(R.dimen.hazelnut_sugar)
    }

    private fun addCaramelAttributes(syrupData: SyrupsData) {
        syrupData.calories = getDimension(R.dimen.caramel_calories)
        syrupData.carbs = getDimension(R.dimen.caramel_carbs)
        syrupData.fats = getDimension(R.dimen.caramel_fats)
        syrupData.protein = getDimension(R.dimen.caramel_protein)
        syrupData.sugar = getDimension(R.dimen.caramel_sugar)
    }

    private fun addVanillaAttributes(syrupData: SyrupsData) {
        syrupData.calories = getDimension(R.dimen.vanilla_calories)
        syrupData.carbs = getDimension(R.dimen.vanilla_carbs)
        syrupData.fats = getDimension(R.dimen.vanilla_fats)
        syrupData.protein = getDimension(R.dimen.vanilla_protein)
        syrupData.sugar = getDimension(R.dimen.vanilla_sugar)
    }

}