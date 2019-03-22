package com.cearleysoftware.byob.ui.fragments.picks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.constants.Constants
import com.cearleysoftware.byob.databinding.FragmentViewPicksDrinkBinding
import com.cearleysoftware.byob.extensions.inflateWithBinding
import com.cearleysoftware.byob.models.Drink
import com.cearleysoftware.byob.ui.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.fragment_view_picks_drink.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

//  Copyright © 2019 Cearley Software. All rights reserved.

class DrinkFragment: Fragment() {

    private val mainViewModel by sharedViewModel<MainViewModel>()
    private var drink: Drink? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        drink = arguments?.getParcelable(Constants.DRINK)
    }

    private lateinit var binding: FragmentViewPicksDrinkBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View {
        binding = inflater.inflateWithBinding(R.layout.fragment_view_picks_drink, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.drink = drink
        backButton.setOnClickListener { mainViewModel.popBackStack() }
    }

    companion object {
        fun newInstance(drink: Drink): DrinkFragment{
            val bundle = bundleOf(Constants.DRINK to drink)
            val fragment = DrinkFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}