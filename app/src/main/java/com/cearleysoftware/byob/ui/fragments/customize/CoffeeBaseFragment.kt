package com.cearleysoftware.byob.ui.fragments.customize

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.extensions.*
import com.cearleysoftware.byob.ui.adapters.CoffeeBaseAdapter
import com.cearleysoftware.byob.ui.viewmodels.CustomDrinkViewModel
import kotlinx.android.synthetic.main.fragment_coffee_base.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

//  Copyright © 2019 Cearley Software. All rights reserved.

class CoffeeBaseFragment: Fragment(){

    private lateinit var coffeeBaseAdapter: CoffeeBaseAdapter
    private val customDrinkViewModel by sharedViewModel<CustomDrinkViewModel>()

    private lateinit var stringArray: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stringArray = resources.getStringArray(R.array.coffee_bases_array)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflateTo(R.layout.fragment_coffee_base, container)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUI()
    }

    private fun setupUI() {

        coffeeBaseAdapter = CoffeeBaseAdapter()
        coffeeBaseAdapter.updateList(getList())
        recyclerView.apply {
            layoutManager = LinearLayoutManager(safeActivity)
            adapter = coffeeBaseAdapter
        }

        customDrinkViewModel.navigateToMilks.observe(this, Observer {
            safeActivity.addFragment(fragment = MilksFragment())
        })
        customDrinkViewModel.showAlertDialog.observe(this, Observer { alertData ->
            safeActivity.showAlertDialog(alertData.title, alertData.message)
        })

        backButton.setOnClickListener { safeActivity.onBackPressed() }

        nextButton.setOnClickListener {
            val index = coffeeBaseAdapter.lastSelectedIndex
            customDrinkViewModel.coffeeBaseNextButtonClicked(index, stringArray)
        }
    }

    private fun getList(): ArrayList<CoffeeBaseData> {
        val dataList = ArrayList<CoffeeBaseData>()
        val array = stringArray
        val base = customDrinkViewModel.customDrinkData.base

        array.forEachIndexed { index, string ->
            val data = CoffeeBaseData(string)
            if (base.contentEquals(string)){
                data.isSelected = true
                coffeeBaseAdapter.lastSelectedIndex = index
            }
            dataList.add(data)
        }
        return dataList
    }

    data class CoffeeBaseData(var base: String, var isSelected: Boolean = false)
}