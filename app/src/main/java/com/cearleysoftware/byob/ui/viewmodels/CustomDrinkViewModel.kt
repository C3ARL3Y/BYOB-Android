package com.cearleysoftware.byob.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cearleysoftware.byob.database.CustomDrinkHelper
import com.cearleysoftware.byob.models.CustomDrink
import com.cearleysoftware.byob.models.MilksData
import com.cearleysoftware.byob.models.SyrupsData
import com.cearleysoftware.byob.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CustomDrinkViewModel(private val customDrinkHelper: CustomDrinkHelper): ViewModel() {

    var customDrinkData = CustomDrink()
    var customizableDrinkToSave: CustomDrink? = null
    val onDrinkSavedToFavorites = SingleLiveEvent<Unit>()
    val onDrinkSavedToFavoritesFailed = SingleLiveEvent<Unit>()
    val navigateToMilks = SingleLiveEvent<Unit>()
    val showAlertDialog = SingleLiveEvent<AlertData>()

    private val disposables = CompositeDisposable()

    val hasFavoriteDrinkToSave: LiveData<Boolean> get() = _hasFavoriteDrinkToSave
    private val _hasFavoriteDrinkToSave = MutableLiveData<Boolean>()

    fun storeExtra(index: Int, stringArray: Array<String>) {
        if (index > -1){
            val extra = stringArray[index]
            customDrinkData.extra = extra
            customizableDrinkToSave = customDrinkData.copy()
            _hasFavoriteDrinkToSave.postValue(true)
        }
    }

    fun saveCustomDrinkToFavorites(name: String) {
        val drink = customizableDrinkToSave
        if (drink != null) {
            drink.name = name
            disposables.add(customDrinkHelper.insertCustomDrink(drink)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        onDrinkSavedToFavorites.call()
                        clearCustomDrink()
                    }, {
                        onDrinkSavedToFavoritesFailed.call()
                    }))
        }
    }

    fun coffeeBaseNextButtonClicked(index: Int, stringArray: Array<String>) {
        if (index < 0){
            showAlertDialog.postValue(AlertData("", "Please select a coffee base"))
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
    }

    fun saveSyrups(list: List<SyrupsData>) {
        customDrinkData.syrups.clear()
        customDrinkData.syrups.addAll(list)
    }

    fun clearCustomDrink() {
        if (customizableDrinkToSave != null){
            customizableDrinkToSave = null
            customDrinkData.clear()
            _hasFavoriteDrinkToSave.postValue(false)
        }
    }

    data class AlertData(val title: String, val message: String)

}