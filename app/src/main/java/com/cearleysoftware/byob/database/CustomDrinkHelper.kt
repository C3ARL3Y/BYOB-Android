package com.cearleysoftware.byob.database

import com.cearleysoftware.byob.models.CustomDrink
import io.reactivex.Observable

interface CustomDrinkHelper {

    fun getCustomDrinks(): Observable<List<CustomDrink>>

    fun insertCustomDrink(customDrink: CustomDrink): Observable<Boolean>
}

class CustomDrinkHelperImplementation(private val customDrinkDao: DrinkDao,
                                      private val milksDao: MilksDao,
                                      private val syrupsDao: SyrupsDao): CustomDrinkHelper {

    override fun getCustomDrinks(): Observable<List<CustomDrink>> {

        return Observable.create { emitter ->
            val customDrinks = customDrinkDao.getAll()
            for(drink in customDrinks){
                val drinkId = drink.drinkId
                val syrups = syrupsDao.getAllForDrink(drinkId)
                val milks = milksDao.getAllForDrink(drinkId)
                drink.syrups.addAll(syrups)
                drink.milks.addAll(milks)
            }
            emitter.onNext(customDrinks)
        }

    }

    override fun insertCustomDrink(customDrink: CustomDrink): Observable<Boolean> {

        return Observable.create { emitter ->
            customDrinkDao.insertDrink(customDrink)
            val milks = customDrink.milks
            for (milk in milks){
                milk.drinkId = customDrink.drinkId
                milksDao.insertMilk(milk)
            }

            val syrups = customDrink.syrups
            for (syrup in syrups){
                syrup.drinkId = customDrink.drinkId
                syrupsDao.insertSyrup(syrup)
            }
            emitter.onNext(true)
        }
    }
}