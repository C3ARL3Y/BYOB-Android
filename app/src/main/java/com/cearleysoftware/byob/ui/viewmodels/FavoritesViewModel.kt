package com.cearleysoftware.byob.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.cearleysoftware.byob.database.CustomDrinkHelper
import com.cearleysoftware.byob.models.CustomDrink
import com.cearleysoftware.byob.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FavoritesViewModel(private val databaseHelper: CustomDrinkHelper): ViewModel() {

    private val disposables = CompositeDisposable()
    val onGetFavoritesResult = SingleLiveEvent<List<CustomDrink>>()
    val onGetFavoritesError = SingleLiveEvent<Throwable>()

    fun getFavorites() {
        disposables.add(databaseHelper.getCustomDrinks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ favorites ->
                    onGetFavoritesResult.postValue(favorites)
                }, { error ->
                    onGetFavoritesError.postValue(error)
                }))
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}