package com.cearleysoftware.byob

import android.app.Application
import com.cearleysoftware.byob.network.api.networkModule
import org.koin.android.ext.android.startKoin

class BYOBApplication: Application() {


    override fun onCreate() {
        super.onCreate()

        val modules = listOf(networkModule)

        startKoin(
                androidContext = this,
                modules = modules
        )
    }

}