package com.cearleysoftware.byob.ui.fragments.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.extensions.*
import com.cearleysoftware.byob.ui.adapters.FavoritesAdapter
import com.cearleysoftware.byob.ui.viewmodels.CustomDrinkViewModel
import com.cearleysoftware.byob.ui.viewmodels.FavoritesViewModel
import kotlinx.android.synthetic.main.fragment_favorites.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

//  Copyright © 2019 Cearley Software. All rights reserved.

class FavoritesFragment: Fragment() {

    private val customDrinkViewModel by sharedViewModel<CustomDrinkViewModel>()
    private val favoritesViewModel by sharedViewModel<FavoritesViewModel>()
    private lateinit var favoritesAdapter: FavoritesAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflateTo(R.layout.fragment_favorites, container)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        favoritesAdapter = FavoritesAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(safeActivity)
            adapter = favoritesAdapter
            addOnItemClick { position, _ ->
                val favorite = favoritesAdapter.getFavoriteForPosition(position)
                safeActivity.addFragment(
                        fragment = FavoriteDetailsFragment.newInstance(favorite)
                )
            }
        }

        favoritesViewModel.onGetFavoritesResult.observe(this, Observer { results ->
            favoritesAdapter.updateData(results)
        })

        favoritesViewModel.onGetFavoritesError.observe(this, Observer {
            safeActivity.showAlertDialog("Error", "Could not load favorites")
        })

        customDrinkViewModel.onDrinkSavedToFavorites.observe(this, Observer {
            favoritesViewModel.getFavorites()
        })

        favoritesViewModel.getFavorites()
    }
}