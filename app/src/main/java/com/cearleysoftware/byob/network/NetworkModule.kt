package com.cearleysoftware.byob.network.api

import com.google.firebase.database.FirebaseDatabase
import org.koin.dsl.module.module

val networkModule = module {

    single<DrinksService> {
        val reference = FirebaseDatabase.getInstance().reference.child("drinks")
        DrinkServiceImplementation(reference)
    }


}