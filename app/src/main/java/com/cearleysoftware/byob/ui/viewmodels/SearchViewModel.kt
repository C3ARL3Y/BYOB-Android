package com.cearleysoftware.byob.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.cearleysoftware.byob.models.Drink
import com.cearleysoftware.byob.network.api.DrinksService
import com.cearleysoftware.byob.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchViewModel(private val drinksService: DrinksService): ViewModel() {

    val hasSearchResults = SingleLiveEvent<List<Drink>>()
    val onSearchResultError = SingleLiveEvent<Throwable>()

    private val disposables = CompositeDisposable()

    fun search(query: String, drinkType: String){
        if (query.count() >= 3) {
            disposables.add(drinksService.searchDrink(drinkType, query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ drinks ->
                        hasSearchResults.postValue(drinks)
                    }, { error ->
                        onSearchResultError.postValue(error)
                    }))
        }
        else{
            hasSearchResults.postValue(emptyList())
        }
    }


    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}