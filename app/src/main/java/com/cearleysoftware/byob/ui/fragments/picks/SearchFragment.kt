package com.cearleysoftware.byob.ui.fragments.picks

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.constants.Constants
import com.cearleysoftware.byob.extensions.*
import com.cearleysoftware.byob.ui.adapters.DrinkSearchAdapter
import com.cearleysoftware.byob.ui.viewmodels.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment: Fragment() {

    private lateinit var drinksAdapter: DrinkSearchAdapter
    private lateinit var drinkType: String
    private val searchViewModel by viewModel<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        drinkType = arguments?.getString(Constants.DRINK_TYPE, "")?: ""
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflateTo(R.layout.fragment_search, container)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        drinksAdapter = DrinkSearchAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(safeActivity)
            adapter = drinksAdapter
            addOnItemClick { position, _ ->
                val drink = drinksAdapter.getSongForPosition(position)
                safeActivity.replaceFragment(fragment = DrinkFragment.newInstance(drink), addToBackStack = true)
            }
        }

        searchViewModel.hasSearchResults.observe(this, Observer { drinks ->
            drinksAdapter.updateData(drinks)
        })

        searchViewModel.onSearchResultError.observe(this, Observer { error ->
            safeActivity.showAlertDialog("Error", "Unable to search drinks.")
        })

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(search: CharSequence?, start: Int, before: Int, count: Int) {
                searchViewModel.search(search.toString(), drinkType)
            }

        })

        btnBack.setOnClickListener { safeActivity.onBackPressed() }
    }

    companion object {
        fun newInstance(drinkType: String): SearchFragment{
            val bundle = bundleOf(Constants.DRINK_TYPE to drinkType)
            val fragment = SearchFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}