package com.cearleysoftware.byob.ui.viewmodels

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cearleysoftware.byob.models.*
import com.cearleysoftware.byob.util.SingleLiveEvent

//  Copyright © 2019 Cearley Software. All rights reserved.

class MainViewModel: ViewModel() {

    private var customizableDrinkToSave: CustomDrink? = null

    var customDrinkData = CustomDrink()

    val navigateToViewDrinks: LiveData<String> get() = _navigateToViewDrinks
    private val _navigateToViewDrinks = MutableLiveData<String>()

    val navigateToDrinkFromPicks: LiveData<Drink> get() = _navigateToDrinkFromPicks
    private val _navigateToDrinkFromPicks = MutableLiveData<Drink>()

    val navigateToCreateDrink = SingleLiveEvent<Drink>()

    val navigateToSteps = SingleLiveEvent<List<String>>()

    val navigateToNutrients = SingleLiveEvent<Nutrients>()

    val navigateToMilks = SingleLiveEvent<Unit>()

    val navigateToSyrups = SingleLiveEvent<Unit>()

    val navigateToExtras = SingleLiveEvent<Unit>()

    val navigateToMainFromExtras = SingleLiveEvent<Unit>()

    val navigateToImageGallery = SingleLiveEvent<(String, Uri) -> Unit>()

    val showEmailDialog = SingleLiveEvent<Unit>()

    val showAlertDialog: LiveData<AlertData> get() = _showAlertDialog
    private val _showAlertDialog = MutableLiveData<AlertData>()

    val navigateToFavoriteDrink = SingleLiveEvent<Unit>()

    val navigateToCoffeeBase = SingleLiveEvent<Unit>()

    val showToast = SingleLiveEvent<String>()

    val popBackStack = SingleLiveEvent<Unit>()

    val login = SingleLiveEvent<LoginData>()

    private val _hasFavoriteDrinkToSave = MutableLiveData<Boolean>()
    val hasFavoriteDrinkToSave: LiveData<Boolean> get() = _hasFavoriteDrinkToSave

    val hasCurrentDrink: Boolean
        get() = customizableDrinkToSave != null

    fun baristaPicksButtonClicked(drinkType: String){
        _navigateToViewDrinks.value = drinkType
    }

    fun currentDrinkClicked(){
        if (customizableDrinkToSave != null) {
            navigateToFavoriteDrink.call()
        }
    }

    fun customizeDrinkClicked(){
        if (customizableDrinkToSave != null){
            customizableDrinkToSave = null
            customDrinkData.clear()
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
        navigateToCreateDrink.postValue(drink)
    }

    fun navigateToImageGallery(onGalleryResult: (String, Uri) -> Unit){
        navigateToImageGallery.postValue(onGalleryResult)
    }

    fun showAlertDialog(title: String, message: String) {
        _showAlertDialog.postValue(AlertData(title, message))
    }

    fun navigateToStepsScreen(steps: List<String>) {
        navigateToSteps.postValue(steps)
    }

    fun navigateToNutrientsScreen(nutrients: Nutrients?) {
        navigateToNutrients.postValue(nutrients)
    }

    fun login(email: String, password: String) {
        login.postValue(LoginData(email, password))
    }

    fun showAddEmailDialog() {
        showEmailDialog.call()
    }

    fun showToast(message: String) {
        showToast.postValue(message)
    }

    fun coffeeBaseNextButtonClicked(index: Int, stringArray: Array<String>) {
        if (index < 0){
           showAlertDialog("", "Please select a coffee base")
        }
        else {
            val base = stringArray[index]
            customDrinkData.base = base
            navigateToMilks.call()
        }
    }

    fun saveMilks(list: List<MilksData>) {
        customDrinkData.milks.clear()
        customDrinkData.milks.addAll(list)
        navigateToSyrups.call()
    }

    fun saveSyrups(list: List<SyrupsData>) {
        customDrinkData.syrups.clear()
        customDrinkData.syrups.addAll(list)
        navigateToExtras.call()
    }

    fun extrasNextButtonClicked(index: Int, stringArray: Array<String>) {
        if (index > -1){
            val extra = stringArray[index]
            customDrinkData.extra = extra
            customizableDrinkToSave = customDrinkData
            navigateToMainFromExtras.call()
        }
    }

    data class AlertData(val title: String, val message: String)

    data class LoginData(val email: String, val password: String)

}