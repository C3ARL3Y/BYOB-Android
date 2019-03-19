package com.cearleysoftware.byob.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cearleysoftware.byob.constants.Constants
import com.cearleysoftware.byob.models.Drink
import com.cearleysoftware.byob.util.SingleLiveEvent

class MainViewModel: ViewModel() {

    private var currentDrink: Drink? = null
    private var customizableDrink: Drink? = null


    val navigateToViewDrinks: LiveData<Int> get() = _navigateToViewDrinks
    private val _navigateToViewDrinks = MutableLiveData<Int>()

    val navigateToFavoriteDrink: LiveData<Drink> get() = _navigateToFavoriteDrink
    private val _navigateToFavoriteDrink = MutableLiveData<Drink>()

    val navigateToCoffeeBase = SingleLiveEvent<Unit>()

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
        _navigateToFavoriteDrink.value = currentDrink
    }

    fun customizeDrinkClicked(){
        if (currentDrink != null){
            currentDrink = null
            customizableDrink = null
        }
        navigateToCoffeeBase.call()
    }
}