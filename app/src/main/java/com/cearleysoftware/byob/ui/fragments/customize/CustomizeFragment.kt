package com.cearleysoftware.byob.ui.fragments.customize

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.databinding.FragmentCustomizeBinding
import com.cearleysoftware.byob.extensions.*
import com.cearleysoftware.byob.ui.fragments.favorites.FavoriteDetailsFragment
import com.cearleysoftware.byob.ui.viewmodels.CreateDrinkViewModel
import com.cearleysoftware.byob.ui.viewmodels.CustomDrinkViewModel
import com.cearleysoftware.byob.ui.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.fragment_customize.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

//  Copyright © 2019 Cearley Software. All rights reserved.

class CustomizeFragment: Fragment() {

    private val customDrinkViewModel by sharedViewModel<CustomDrinkViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflateTo(R.layout.fragment_customize, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {

        currentDrinkButton.setOnClickListener {
            val drink = customDrinkViewModel.customizableDrinkToSave
            if (drink != null) {
                safeActivity.replaceFragment(fragment = FavoriteDetailsFragment.newInstance(drink), addToBackStack = true)
            }
        }
        customizeDrinkButton.setOnClickListener {
            customDrinkViewModel.clearCustomDrink()
            safeActivity.replaceFragment(fragment = CoffeeBaseFragment(), addToBackStack = true)
        }
        customDrinkViewModel.hasFavoriteDrinkToSave.observe(this, Observer { hasDrink ->
            if (hasDrink){
                currentDrinkButton.visibility = View.VISIBLE
                customizeDrinkButton.text = resources.getText(R.string.override_drink)
            }
            else{
                currentDrinkButton.visibility = View.INVISIBLE
                customizeDrinkButton.text = resources.getText(R.string.customize_drink)
            }
        })
    }

}