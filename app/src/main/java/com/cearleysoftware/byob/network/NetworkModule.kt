package com.cearleysoftware.byob.network.api

import com.google.firebase.database.FirebaseDatabase
import org.koin.dsl.module.module

//  Copyright © 2019 Cearley Software. All rights reserved.

val networkModule = module {

    single<DrinksService> {
        val reference = FirebaseDatabase.getInstance().reference.child("drinks")
        DrinkServiceImplementation(reference)
    }


}