package com.cearleysoftware.byob.ui.fragments.customize

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.ui.adapters.MilksAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.cearleysoftware.byob.extensions.*
import com.cearleysoftware.byob.ui.viewmodels.CustomDrinkViewModel
import com.cearleysoftware.byob.util.MilkUtils
import kotlinx.android.synthetic.main.fragment_milks.*

//  Copyright © 2019 Cearley Software. All rights reserved.

class MilksFragment: Fragment() {

    private val customDrinkViewModel by sharedViewModel<CustomDrinkViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflateTo(R.layout.fragment_milks, container)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        val customDrinkMilks = customDrinkViewModel.customDrinkData.milks
        val list = MilkUtils.getMilksData(safeActivity, customDrinkMilks)
        val milkAdapter = MilksAdapter(list)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(safeActivity)
            adapter = milkAdapter
        }

        backButton.setOnClickListener { safeActivity.onBackPressed() }

        nextButton.setOnClickListener {
            customDrinkViewModel.saveMilks(milkAdapter.list)
            safeActivity.addFragment(fragment = SyrupsFragment())
        }
    }

}