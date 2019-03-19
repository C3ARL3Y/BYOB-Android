package com.cearleysoftware.byob

import android.app.Application
import com.cearleysoftware.byob.network.api.networkModule
import com.cearleysoftware.byob.ui.viewmodels.viewModelsModule
import org.koin.android.ext.android.startKoin

//  Copyright © 2019 Cearley Software. All rights reserved.

class BYOBApplication: Application() {


    override fun onCreate() {
        super.onCreate()

        val modules = listOf(
                networkModule,
                viewModelsModule
        )

        startKoin(
                androidContext = this,
                modules = modules
        )
    }

}