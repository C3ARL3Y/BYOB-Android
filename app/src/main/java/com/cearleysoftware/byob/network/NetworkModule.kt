package com.cearleysoftware.byob.network.api

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import org.koin.dsl.module.module

//  Copyright © 2019 Cearley Software. All rights reserved.

val networkModule = module {

    single<ImageService>{
        val storage = FirebaseStorage.getInstance().reference.child("images/")
        ImageServiceImplementation(storage, get())
    }

    single<DrinksService> {
        val reference = FirebaseDatabase.getInstance().reference.child("drinks")
        DrinkServiceImplementation(reference, get())
    }

    single<AuthenticationService>{
        AuthenticationServiceImplementation(FirebaseAuth.getInstance())
    }

    single<EmailService>{
        val storage = FirebaseDatabase.getInstance().reference.child("emails")
        EmailServiceImplementation(storage)
    }
}