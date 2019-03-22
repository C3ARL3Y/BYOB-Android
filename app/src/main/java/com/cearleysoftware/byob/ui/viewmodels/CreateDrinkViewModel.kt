package com.cearleysoftware.byob.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cearleysoftware.byob.models.Drink
import com.cearleysoftware.byob.models.Nutrients
import com.cearleysoftware.byob.network.api.DrinksService
import com.cearleysoftware.byob.util.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable

class CreateDrinkViewModel(private val drinkService: DrinksService): ViewModel() {

    val drinkData = CreateDrinkData()
    var savedDrink: Drink? = null
    val disposables = CompositeDisposable()

    val onDrinkSaved = SingleLiveEvent<Unit>()

    val onDrinkSaveFailed = SingleLiveEvent<Unit>()

    fun saveDrink(id: String, name: String, description: String, type: String){
        val drink = Drink(id, name, drinkData.imageUrl, description, drinkData.nutrients, drinkData.steps, type)
        disposables.add(drinkService.addDrink(drink)
                .subscribe({ drinkResult ->
                    savedDrink = drinkResult
                    onDrinkSaved.call()
                }, { error ->
                    onDrinkSaveFailed.call()
                    error.printStackTrace()
                }))


    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    data class CreateDrinkData(var imageUrl: String = "",
                               var nutrients: Nutrients? = null,
                               var steps: MutableList<String> = mutableListOf()){

        fun clear(){
            imageUrl = ""
            nutrients = null
            steps.clear()
        }

    }
}