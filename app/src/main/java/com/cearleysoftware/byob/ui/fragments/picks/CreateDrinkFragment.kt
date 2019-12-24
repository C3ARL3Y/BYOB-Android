package com.cearleysoftware.byob.ui.fragments.picks

import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.constants.Constants
import com.cearleysoftware.byob.databinding.FragmentCreateDrinkBinding
import com.cearleysoftware.byob.extensions.*
import com.cearleysoftware.byob.models.Drink
import com.cearleysoftware.byob.ui.viewmodels.CreateDrinkViewModel
import com.cearleysoftware.byob.ui.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.fragment_create_drink.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

//  Copyright © 2019 Cearley Software. All rights reserved.

class CreateDrinkFragment: Fragment() {

    private lateinit var binding: FragmentCreateDrinkBinding
    private val createDrinkViewModel by sharedViewModel<CreateDrinkViewModel> ()
    private val mainViewModel by sharedViewModel<MainViewModel>()

    private lateinit var drink: Drink

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        drink = arguments?.getParcelable(Constants.DRINK)?: Drink()
        val drinkData = createDrinkViewModel.drinkData
        drinkData.nutrients = drink.nutrients
        drinkData.steps = drink.steps
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View {
        binding = inflater.inflateWithBinding(R.layout.fragment_create_drink, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        nutrientsButton.setOnClickListener {
            safeActivity.replaceFragment(
                    fragment = NutrientsFragment.newInstance(drink.nutrients),
                    addToBackStack = true
            )
        }

        stepsButton.setOnClickListener {
            safeActivity.replaceFragment(
                    fragment = StepsFragment.newInstance(drink.steps),
                    addToBackStack = true
            )
        }
        backButton.setOnClickListener { safeActivity.onBackPressed() }

        drinkImageView.setOnClickListener {
            mainViewModel.navigateToImageGallery { path: String, _: Uri ->
                drinkImageView.loadImage(path)
                drink.imageURL = path
                createDrinkViewModel.drinkData.imageUrl = path
            }
        }

        saveButton.setOnClickListener {
            progress.show()
            createDrinkViewModel.saveDrink(
                    drink.id,
                nameView.text.toString(),
                discriptionView.text.toString(),
                    drink.type,
                    drink.imageURL
            )
        }

        nameView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                drink.name = text?.toString()?: ""
            }
        })

        discriptionView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                drink.description = text?.toString()?: ""
            }
        })

        createDrinkViewModel.onDrinkSaved.observe(this, Observer { event ->
            if (event.getContentIfNotHandled() != null) {
                progress.hide()
                createDrinkViewModel.loadDrinks(drink.type)
                safeActivity.onBackPressed()
            }
        })
        createDrinkViewModel.onDrinkSaveFailed.observe(this, Observer {
            progress.hide()
            safeActivity.showAlertDialog("Error", "Could not save drink.")
        })
        binding.drink = drink
    }

    companion object {
        fun newInstance(drink: Drink?): CreateDrinkFragment{
            val bundle = bundleOf(Constants.DRINK to drink)
            val fragment = CreateDrinkFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}