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
import com.cearleysoftware.byob.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CreateDrinkViewModel(private val drinkService: DrinksService): ViewModel() {

    val drinkData = CreateDrinkData()
    var savedDrink: Drink? = null
    val disposables = CompositeDisposable()

    val onDrinkSaved = SingleLiveEvent<Unit>()
    val onDrinkSaveFailed = SingleLiveEvent<Unit>()

    val onDrinkRemoved = SingleLiveEvent<Unit>()
    val onDrinkRemoveFailed = SingleLiveEvent<Unit>()

    val navigateToCreateDrink = SingleLiveEvent<Drink>()

    fun saveDrink(id: String, name: String, description: String, type: String){
        val drink = Drink(id, name, drinkData.imageUrl, description, drinkData.nutrients, drinkData.steps, type)
        disposables.add(drinkService.addDrink(drink)
                .subscribe({ drinkResult ->
                    savedDrink = drinkResult
                    onDrinkSaved.call()
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

                R.id.popup_drink_delete -> removeDrink(drink.id)
            }
            true
        }
        popupMenu.inflate(R.menu.menu_popup_drink)
        popupMenu.show()
    }

    private fun removeDrink(drinkId: String) {
        disposables.add(drinkService.deleteDrink(drinkId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    if (result){
                        onDrinkRemoved.call()
                    }
                    else{
                        onDrinkRemoveFailed.call()
                    }
                }, { error ->
                    error.printStackTrace()
                    onDrinkRemoveFailed.call()
                }))
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