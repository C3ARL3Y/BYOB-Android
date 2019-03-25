package com.cearleysoftware.byob.network.api

import com.cearleysoftware.byob.models.Drink
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import io.reactivex.Observable
import java.util.*

//  Copyright © 2019 Cearley Software. All rights reserved.

interface DrinksService{

    fun addDrink(drink: Drink, imageUrl: String): Observable<Boolean>

    fun getDrinks(drinkType: String): Observable<List<Drink>>

    fun deleteDrink(drinkType: String, drinkId: String): Observable<Boolean>
}

class DrinkServiceImplementation(private val database: DatabaseReference, private val imageService: ImageService): DrinksService{

    override fun addDrink(drink: Drink, imageUrl: String): Observable<Boolean> {
        return imageService.addImage(imageUrl)
                .switchMap { resultImageUrl ->
                    if (resultImageUrl.isNotBlank()) {
                        drink.imageURL = resultImageUrl
                    }
                    Observable.create<Boolean> { emitter ->
                        var id = drink.id
                        if (id.isBlank()){
                            id = database.child(drink.type).push().key?: ""
                        }
                        val timeStamp = drink.timestamp
                        if (timeStamp <=0){
                            drink.timestamp = System.currentTimeMillis()/1000
                        }
                        database.child(drink.type).child(id).setValue(drink)
                                .addOnSuccessListener {
                                    emitter.onNext(true)
                                }
                                .addOnFailureListener { error ->
                                    emitter.onError(error)
                                }
                    }
                }
    }

    override fun getDrinks(drinkType: String): Observable<List<Drink>> {
        return Observable.create { emitter ->
            val drinks = ArrayList<Drink>()
            database.child(drinkType).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (drinkSnapShot in dataSnapshot.children){
                        val drink = drinkSnapShot?.getValue(Drink::class.java)?: continue
                        drink.id = drinkSnapShot.key?: ""
                        drinks.add(drink)
                    }
                    emitter.onNext(drinks)
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    emitter.onError(error.toException())
                }
            })
        }

    }

    override fun deleteDrink(drinkType: String, drinkId: String): Observable<Boolean> {
        return Observable.create { emitter ->
            database.child(drinkType).child(drinkId).removeValue()
                    .addOnSuccessListener {
                        emitter.onNext(true)

                    }
                    .addOnFailureListener { error ->
                        emitter.onError(error)
                    }
        }
    }

}