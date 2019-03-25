package com.cearleysoftware.byob.ui.fragments.picks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.constants.DrinkTypes
import com.cearleysoftware.byob.databinding.FragmentBaristaPicksBinding
import com.cearleysoftware.byob.extensions.inflateWithBinding
import com.cearleysoftware.byob.ui.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.fragment_barista_picks.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

//  Copyright © 2019 Cearley Software. All rights reserved.

class BaristaPicksFragment: Fragment() {

    private val mainViewModel by sharedViewModel<MainViewModel>()

    private lateinit var binding: FragmentBaristaPicksBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View{
        binding = inflater.inflateWithBinding(R.layout.fragment_barista_picks, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        hotDrinks.setOnClickListener { mainViewModel.baristaPicksButtonClicked(DrinkTypes.HOT_DRINKS) }
        icedDrinks.setOnClickListener { mainViewModel.baristaPicksButtonClicked(DrinkTypes.ICED_DRINKS) }
        teas.setOnClickListener { mainViewModel.baristaPicksButtonClicked(DrinkTypes.TEAS) }
        binding.viewModel = mainViewModel
    }
}