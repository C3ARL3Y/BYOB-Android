package com.cearleysoftware.byob.ui.fragments.customize

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.databinding.FragmentCustomizeBinding
import com.cearleysoftware.byob.extensions.inflateWithBinding
import com.cearleysoftware.byob.ui.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.fragment_customize.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

//  Copyright © 2019 Cearley Software. All rights reserved.

class CustomizeFragment: Fragment() {

    private lateinit var binding: FragmentCustomizeBinding
    private val mainViewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View{
        binding = inflater.inflateWithBinding(R.layout.fragment_customize, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {

        binding.mainViewModel = mainViewModel
        if (mainViewModel.hasCurrentDrink){
            currentDrinkButton.visibility = View.VISIBLE
            customizeDrinkButton.text = resources.getText(R.string.override_drink)
        }
        else{
            currentDrinkButton.visibility = View.INVISIBLE
            customizeDrinkButton.text = resources.getText(R.string.customize_drink)
        }
    }
}