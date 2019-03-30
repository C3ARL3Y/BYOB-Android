package com.cearleysoftware.byob.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.cearleysoftware.byob.database.CustomDrinkHelper
import io.reactivex.disposables.CompositeDisposable

class SearchViewModel(private val databaseHelper: CustomDrinkHelper): ViewModel() {

    private val disposables = CompositeDisposable()
    fun search(query: String){

    }
}