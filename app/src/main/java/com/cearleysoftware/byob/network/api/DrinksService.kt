package com.cearleysoftware.byob.network.api

import com.cearleysoftware.byob.models.Drink
import com.cearleysoftware.byob.util.MockDataManager
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import io.reactivex.Observable

//  Copyright © 2019 Cearley Software. All rights reserved.

interface DrinksService{

    fun addDrink(drink: Drink): Observable<Drink>

    fun updateDrink(drink: Drink): Observable<Task<Void>>

    fun getDrinks(drinkType: String): Observable<List<Drink>>

    fun deleteDrink(drinkId: Int): Observable<Task<Void>>
}

class DrinkServiceImplementation(database: DatabaseReference): DrinksService{

    override fun addDrink(drink: Drink): Observable<Drink> {
        return Observable.create { emitter ->
            emitter.onNext(drink)
        }
    }

    override fun updateDrink(drink: Drink): Observable<Task<Void>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDrinks(drinkType: String): Observable<List<Drink>> {
        return Observable.create { emitter ->
            val drinks = /*ArrayList<Drink>()*/ MockDataManager.getMockDrinks()// todo: Get drinks from firebase

            emitter.onNext(drinks)
        }

    }

    override fun deleteDrink(drinkId: Int): Observable<Task<Void>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}