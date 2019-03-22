package com.cearleysoftware.byob.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cearleysoftware.byob.models.Drink
import com.cearleysoftware.byob.util.SingleLiveEvent

class MainViewModel: ViewModel() {

    private var currentFavoriteDrink: Drink? = null
    private var customizableDrink: Drink? = null


    val navigateToViewDrinks: LiveData<String> get() = _navigateToViewDrinks
    private val _navigateToViewDrinks = MutableLiveData<String>()

    val navigateToDrinkFromPicks: LiveData<Drink> get() = _navigateToDrinkFromPicks
    private val _navigateToDrinkFromPicks = MutableLiveData<Drink>()

    val navigateToCreateDrink: LiveData<Drink?> get() = _navigateToCreateDrink
    private val _navigateToCreateDrink = MutableLiveData<Drink?>()

    val navigateToFavoriteDrink = SingleLiveEvent<Unit>()

    val navigateToCoffeeBase = SingleLiveEvent<Unit>()

    val popBackStack = SingleLiveEvent<Unit>()

    private val _hasFavoriteDrinkToSave = MutableLiveData<Boolean>()
    val hasFavoriteDrinkToSave: LiveData<Boolean> get() = _hasFavoriteDrinkToSave

    val hasCurrentDrink: Boolean
        get() = currentFavoriteDrink != null

    fun baristaPicksButtonClicked(drinkType: String){
        _navigateToViewDrinks.value = drinkType
    }

    fun currentDrinkClicked(){
        if (currentFavoriteDrink != null) {
            navigateToFavoriteDrink.call()
        }
    }

    fun customizeDrinkClicked(){
        if (currentFavoriteDrink != null){
            currentFavoriteDrink = null
            customizableDrink = null
        }
        navigateToCoffeeBase.call()
    }

    fun drinkFromPicksClicked(drink: Drink) {
        _navigateToDrinkFromPicks.value = drink
    }

    fun popBackStack() {
        popBackStack.call()
    }

    fun navigateToCreateDrink(drink: Drink?) {
        _navigateToCreateDrink.value = drink
    }
}