package com.cearleysoftware.byob.ui.fragments.picks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.constants.Constants
import com.cearleysoftware.byob.constants.DrinkTypes
import com.cearleysoftware.byob.extensions.*
import com.cearleysoftware.byob.models.Drink
import com.cearleysoftware.byob.network.api.AuthenticationService
import com.cearleysoftware.byob.network.api.DrinksService
import com.cearleysoftware.byob.ui.adapters.DrinkSearchAdapter
import com.cearleysoftware.byob.ui.viewmodels.CreateDrinkViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_view_drinks.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

//  Copyright © 2019 Cearley Software. All rights reserved.

class ViewDrinksFragment: Fragment() {

    private val drinksService by inject<DrinksService>()
    private val authenticationService by inject<AuthenticationService>()
    private val createDrinkViewModel by sharedViewModel<CreateDrinkViewModel>()
    private val disposables = CompositeDisposable()

    private lateinit var drinksAdapter: DrinkSearchAdapter
    private lateinit var drinkType: String

    private var isLoggedIn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isLoggedIn = authenticationService.getUser() != null
        drinkType = arguments?.getString(Constants.DRINK_TYPE, DrinkTypes.HOT_DRINKS)?: DrinkTypes.HOT_DRINKS
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflateTo(R.layout.fragment_view_drinks, container)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUI()
    }

    override fun onStart() {
        super.onStart()
        loadDrinks()
    }

    private fun setupUI() {
        drinksAdapter = DrinkSearchAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(safeActivity)
            adapter = drinksAdapter
            addOnItemClick { position, _ ->
                val drink = drinksAdapter.getSongForPosition(position)
                safeActivity.replaceFragment(
                        fragment = DrinkFragment.newInstance(drink),
                        addToBackStack = true
                )
            }

            if (isLoggedIn) {
                addOnItemLongClick { position, view ->
                    val drink = drinksAdapter.getSongForPosition(position)
                    createDrinkViewModel.drinkFromPicksLongClicked(drink, view)
                }
            }
        }
        if (isLoggedIn) {
            newDrinkButton.show()
            newDrinkButton.setOnClickListener {
                safeActivity.replaceFragment(
                        fragment = CreateDrinkFragment.newInstance(Drink(type = drinkType)),
                        addToBackStack = true
                )
            }
        }
        else{
            newDrinkButton.hide()
        }
        doneButton.setOnClickListener { safeActivity.onBackPressed() }

        createDrinkViewModel.navigateToCreateDrink.observe(this, Observer { drink ->
            safeActivity.replaceFragment(
                    fragment = CreateDrinkFragment.newInstance(drink),
                    addToBackStack = true
            )
        })

        createDrinkViewModel.onDrinkRemoved.observe(this, Observer {
            safeActivity.showToast("Drink removed")
            loadDrinks()
        })
        createDrinkViewModel.onDrinkRemoveFailed.observe(this, Observer {
            safeActivity.showToast("Unable to remove drink")
        })

        searchView.setOnClickListener { safeActivity.addFragment(fragment = SearchFragment.newInstance(drinkType)) }
    }

    private fun loadDrinks() {
        disposables.add(drinksService.getDrinks(drinkType)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    progress.visibility = View.VISIBLE
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ drinks ->
                    progress.visibility = View.GONE
                    drinksAdapter.updateData(drinks)
                }, { error ->
                    error.printStackTrace()
                }))
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

    companion object {

        fun newInstance(drinkType: String): ViewDrinksFragment {
            val bundle = bundleOf(Constants.DRINK_TYPE to drinkType)
            val fragment = ViewDrinksFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}