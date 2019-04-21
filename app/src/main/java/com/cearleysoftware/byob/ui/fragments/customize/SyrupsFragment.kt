package com.cearleysoftware.byob.ui.fragments.customize

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.extensions.*
import com.cearleysoftware.byob.ui.adapters.SyrupsAdapter
import com.cearleysoftware.byob.ui.viewmodels.CustomDrinkViewModel
import com.cearleysoftware.byob.util.SyrupUtils
import kotlinx.android.synthetic.main.fragment_syrups.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

//  Copyright © 2019 Cearley Software. All rights reserved.

class SyrupsFragment: Fragment() {

    private val customDrinkViewModel by sharedViewModel<CustomDrinkViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflateTo(R.layout.fragment_syrups, container)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUI()
    }

    private fun setupUI() {

        val customDrinkSyrups = customDrinkViewModel.customDrinkData.syrups
        val syrupsList = SyrupUtils.getSyrupList(safeActivity, customDrinkSyrups)
        val syrupAdapter = SyrupsAdapter(syrupsList)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(safeActivity)
            adapter = syrupAdapter
        }

        backButton.setOnClickListener { safeActivity.onBackPressed() }

        nextButton.setOnClickListener {
            customDrinkViewModel.saveSyrups(syrupAdapter.list)
            activity.addFragment(fragment = ExtrasFragment())
        }
    }
}