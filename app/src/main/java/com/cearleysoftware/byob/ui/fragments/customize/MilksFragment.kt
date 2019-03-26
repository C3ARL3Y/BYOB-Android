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

        val adapter = MilksAdapter(getList())
    }

    private fun getList(): List<MilksData> {
        val customDrinkMilks = mainViewModel.customDrinkData.milks

        if (customDrinkMilks.isNotEmpty()){
            return customDrinkMilks
        }
        else {
            val array = resources.getStringArray(R.array.milks_array)
            val list = ArrayList<MilksData>()
            for (milk in array) {
                val milkData = MilksData(milk)
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun addSoyAttributes(milkData: MilksData) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun addOnePercentAttributes(milkData: MilksData) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun addNonFatAttributes(milkData: MilksData) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun addHeavyCreamAttributes(milkData: MilksData) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun addHalfAndHalfAttributes(milkData: MilksData) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun addCoconutMilkAttributes(milkData: MilksData) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun addAlmondMilkAttributes(milkData: MilksData) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun addWholeMilkAttributes(milkData: MilksData) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}