package com.cearleysoftware.byob.ui.viewmodels

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cearleysoftware.byob.constants.Constants
import com.cearleysoftware.byob.models.Drink
import com.cearleysoftware.byob.util.SingleLiveEvent

class MainViewModel: ViewModel() {

    private var currentFavoriteDrink: Drink? = null
    private var customizableDrink: Drink? = null


    val navigateToViewDrinks: LiveData<Int> get() = _navigateToViewDrinks
    private val _navigateToViewDrinks = MutableLiveData<Int>()

    val navigateToFavoriteDrink = SingleLiveEvent<Unit>()

    val navigateToCoffeeBase = SingleLiveEvent<Unit>()

    private val _hasFavoriteDrinkToSave = MutableLiveData<Boolean>()
    val hasFavoriteDrinkToSave: LiveData<Boolean> get() = _hasFavoriteDrinkToSave

    val hasCurrentDrink: Boolean
        get() = currentFavoriteDrink != null

    fun hotDrinksClicked(){
        _navigateToViewDrinks.value = Constants.HOT_DRINKS
    }

    fun icedDrinksClicked(){
        _navigateToViewDrinks.value = Constants.ICED_DRINKS
    }

    fun teasClicked(){
        _navigateToViewDrinks.value = Constants.TEAS
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
}