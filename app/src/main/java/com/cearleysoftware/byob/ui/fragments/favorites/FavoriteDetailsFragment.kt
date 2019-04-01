package com.cearleysoftware.byob.ui.fragments.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.constants.Constants
import com.cearleysoftware.byob.databinding.FragmentFavoriteDetailsBinding
import com.cearleysoftware.byob.extensions.inflateWithBinding
import com.cearleysoftware.byob.extensions.safeActivity
import com.cearleysoftware.byob.models.CustomDrink
import com.cearleysoftware.byob.ui.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.fragment_favorite_details.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FavoriteDetailsFragment: Fragment() {

    private var favorite: CustomDrink? = null
    private lateinit var binding: FragmentFavoriteDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favorite = arguments?.getParcelable(Constants.FAVORITE)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View {
        binding = inflater.inflateWithBinding(R.layout.fragment_favorite_details, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.favorite = favorite
        backButton.setOnClickListener { safeActivity.onBackPressed() }
    }


    companion object {

        fun newInstance(favorite: CustomDrink): FavoriteDetailsFragment{
            val bundle = bundleOf(Constants.FAVORITE to favorite)
            val fragment = FavoriteDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}