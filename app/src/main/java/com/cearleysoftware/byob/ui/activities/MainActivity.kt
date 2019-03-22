package com.cearleysoftware.byob.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.databinding.MainActivityBinding
import com.cearleysoftware.byob.extensions.popBackStack
import com.cearleysoftware.byob.extensions.replaceFragment
import com.cearleysoftware.byob.extensions.setDataBindingContentView
import com.cearleysoftware.byob.ui.fragments.MainFragment
import com.cearleysoftware.byob.ui.fragments.picks.CreateDrinkFragment
import com.cearleysoftware.byob.ui.fragments.picks.DrinkFragment
import com.cearleysoftware.byob.ui.fragments.picks.ViewDrinksFragment
import com.cearleysoftware.byob.ui.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

//  Copyright © 2019 Cearley Software. All rights reserved.

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    private var binding: MainActivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setDataBindingContentView(R.layout.main_activity)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setupUI()
    }

    private fun setupUI() {
        viewModel.navigateToViewDrinks.observe(this, Observer { drinkType ->
            replaceFragment(fragment = ViewDrinksFragment.newInstance(drinkType), addToBackStack = true)
        })

        viewModel.navigateToDrinkFromPicks.observe(this, Observer { drink ->
            replaceFragment(fragment = DrinkFragment.newInstance(drink), addToBackStack = true)
        })

        viewModel.navigateToCreateDrink.observe(this, Observer { drink ->
            replaceFragment(fragment = CreateDrinkFragment.newInstance(drink), addToBackStack = true)
        })

        viewModel.navigateToFavoriteDrink.observe(this, Observer { drinkType ->
            Log.d("favoriteDrink", "favoriteDrink")

        })

        viewModel.navigateToCoffeeBase.observe(this, Observer { drinkType ->
            Log.d("coffeeBase", "coffeebase")

        })

        viewModel.popBackStack.observe(this, Observer {
            popBackStack()
        })
        replaceFragment(fragment = MainFragment())

    }
}
