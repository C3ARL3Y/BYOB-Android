package com.cearleysoftware.byob.ui.viewmodels

import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.models.Drink
import com.cearleysoftware.byob.models.Nutrients
import com.cearleysoftware.byob.network.api.DrinksService
import com.cearleysoftware.byob.util.Event
import com.cearleysoftware.byob.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_view_drinks.*

//  Copyright © 2019 Cearley Software. All rights reserved.

class CreateDrinkViewModel(private val drinkService: DrinksService): ViewModel() {

    val drinkData = CreateDrinkData()
    private val disposables = CompositeDisposable()

    val onDrinkSaved: LiveData<Event<Drink>> get() = _onDrinkSaved
    private val _onDrinkSaved = MutableLiveData<Event<Drink>>()

    val onDrinkSaveFailed = SingleLiveEvent<Unit>()

    val onDrinkRemoved = SingleLiveEvent<Unit>()
    val onDrinkRemoveFailed = SingleLiveEvent<Unit>()

    val onLoadDrinksResult: LiveData<Event<List<Drink>>> get() = _onLoadDrinksResult
    private val _onLoadDrinksResult = MutableLiveData<Event<List<Drink>>>()

    val navigateToCreateDrink = SingleLiveEvent<Drink>()

    fun saveDrink(id: String, name: String, description: String, type: String, originalImageUrl: String){
        val drinkDataImageUrl = drinkData.imageUrl
        val drink = Drink(id, name, originalImageUrl, description, drinkData.nutrients?: Nutrients(), drinkData.steps, type)
        disposables.add(drinkService.addDrink(drink, drinkDataImageUrl)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _onDrinkSaved.postValue(Event(drink))
                    drinkData.clear()
                }, { error ->
                    onDrinkSaveFailed.call()
                    error.printStackTrace()
                }))


    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun drinkFromPicksLongClicked(drink: Drink, view: View) {
        val popupMenu = PopupMenu(view.context, view)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {

                R.id.popup_drink_edit ->{
                    navigateToCreateDrink.postValue(drink)
                }

                R.id.popup_drink_delete -> removeDrink(drink.type, drink.id)
            }
            true
        }
        popupMenu.inflate(R.menu.menu_popup_drink)
        popupMenu.show()
    }

    private fun removeDrink(drinkType: String, drinkId: String) {
        disposables.add(drinkService.deleteDrink(drinkType, drinkId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onDrinkRemoved.call()
                }, { error ->
                    error.printStackTrace()
                    onDrinkRemoveFailed.call()
                }))
    }

    fun loadDrinks(drinkType: String) {
        disposables.add(drinkService.getDrinks(drinkType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ drinks ->
                    _onLoadDrinksResult.postValue(Event(drinks))
                }, { error ->
                    error.printStackTrace()
                }))
    }

    data class CreateDrinkData(var imageUrl: String = "",
                               var nutrients: Nutrients? = null,
                               var steps: ArrayList<String> = ArrayList()){

        fun clear(){
            imageUrl = ""
            nutrients = null
            steps.clear()
        }

    }
}