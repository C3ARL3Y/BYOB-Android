package com.cearleysoftware.byob.ui.fragments.picks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.constants.Constants
import com.cearleysoftware.byob.databinding.FragmentCreateDrinkBinding
import com.cearleysoftware.byob.extensions.inflateWithBinding
import com.cearleysoftware.byob.models.Drink

class CreateDrinkFragment: Fragment() {

    private lateinit var binding: FragmentCreateDrinkBinding
    private var drink: Drink? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        drink = arguments?.getParcelable(Constants.DRINK)
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