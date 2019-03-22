package com.cearleysoftware.byob.ui.fragments.picks

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.cearleysoftware.byob.constants.Constants
import com.cearleysoftware.byob.models.Drink

class CreateDrinkFragment: Fragment() {


    companion object {
        fun newInstance(drink: Drink?): CreateDrinkFragment{
            val bundle = bundleOf(Constants.DRINK to drink)
            val fragment = CreateDrinkFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}