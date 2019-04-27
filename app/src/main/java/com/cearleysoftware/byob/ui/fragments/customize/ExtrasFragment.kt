package com.cearleysoftware.byob.ui.fragments.customize

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.extensions.inflateTo
import com.cearleysoftware.byob.extensions.popAllInBackStack
import com.cearleysoftware.byob.extensions.safeActivity
import com.cearleysoftware.byob.extensions.showAlertDialog
import com.cearleysoftware.byob.models.ExtrasData
import com.cearleysoftware.byob.ui.adapters.ExtrasAdapter
import com.cearleysoftware.byob.ui.viewmodels.CustomDrinkViewModel
import kotlinx.android.synthetic.main.fragment_extras.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

//  Copyright © 2019 Cearley Software. All rights reserved.

class ExtrasFragment: Fragment() {

    private val customDrinkViewModel by sharedViewModel<CustomDrinkViewModel>()
    private lateinit var stringArray: Array<String>
    private lateinit var extrasAdapter: ExtrasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stringArray = resources.getStringArray(R.array.extras_array)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflateTo(R.layout.fragment_extras, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        extrasAdapter = ExtrasAdapter()
        extrasAdapter.updateList(getList())
        recyclerView.apply {
            layoutManager = LinearLayoutManager(safeActivity)
            adapter = extrasAdapter
        }

        backButton.setOnClickListener { safeActivity.onBackPressed() }

        nextButton.setOnClickListener {
            val index = extrasAdapter.lastSelectedIndex
            if (index < 0){
                safeActivity.showAlertDialog("", "Please select extra.")
            }
            else {
                customDrinkViewModel.storeExtra(index, stringArray)
                safeActivity.popAllInBackStack()
            }
        }
    }

    private fun getList(): ArrayList<ExtrasData> {
        val dataList = ArrayList<ExtrasData>()
        val array = stringArray
        val extra = customDrinkViewModel.customDrinkData.extra

        array.forEachIndexed { index, string ->
            val data = ExtrasData(string)
            if (extra.contentEquals(string)){
                data.isSelected = true
                extrasAdapter.lastSelectedIndex = index
            }
            dataList.add(data)
        }
        return dataList
    }
}