package com.cearleysoftware.byob.ui.fragments.picks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.constants.DrinkTypes
import com.cearleysoftware.byob.extensions.inflateTo
import com.cearleysoftware.byob.extensions.replaceFragment
import com.cearleysoftware.byob.extensions.safeActivity
import kotlinx.android.synthetic.main.fragment_barista_picks.*

//  Copyright © 2019 Cearley Software. All rights reserved.

class BaristaPicksFragment: Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflateTo(R.layout.fragment_barista_picks, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        hotDrinks.setOnClickListener {
            safeActivity.replaceFragment(
                    fragment = ViewDrinksFragment.newInstance(DrinkTypes.HOT_DRINKS),
                    addToBackStack = true
            )
        }
        icedDrinks.setOnClickListener {
            safeActivity.replaceFragment(
                    fragment = ViewDrinksFragment.newInstance(DrinkTypes.ICED_DRINKS),
                    addToBackStack = true
            )
        }
        teas.setOnClickListener {
            safeActivity.replaceFragment(
                    fragment = ViewDrinksFragment.newInstance(DrinkTypes.TEAS),
                    addToBackStack = true
            )
        }
    }
}