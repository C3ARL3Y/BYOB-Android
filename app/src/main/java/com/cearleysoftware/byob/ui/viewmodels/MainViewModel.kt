package com.cearleysoftware.byob.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cearleysoftware.byob.constants.Constants

class MainViewModel: ViewModel() {

    val navigateToViewDrinks: LiveData<Int> get() = _navigateToViewDrinks
    private val _navigateToViewDrinks = MutableLiveData<Int>()

    fun hotDrinksClicked(){
        _navigateToViewDrinks.value = Constants.HOT_DRINKS
    }

    fun icedDrinksClicked(){
        _navigateToViewDrinks.value = Constants.ICED_DRINKS
    }

    fun teasClicked(){
        _navigateToViewDrinks.value = Constants.TEAS
    }
}