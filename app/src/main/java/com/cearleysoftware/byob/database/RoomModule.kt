package com.cearleysoftware.byob.database

import android.app.Application
import androidx.room.Room
import org.koin.dsl.module.module

val roomModule = module {

    single {
        Room.databaseBuilder(get<Application>(), BYOBDatabase::class.java, "custom_drink.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
    }

    factory { get<BYOBDatabase>().customDrinkDao() }

    factory { get<BYOBDatabase>().syrupsDao() }

    factory { get<BYOBDatabase>().milksDao() }

    factory<CustomDrinkHelper> {
        CustomDrinkHelperImplementation(get(), get(), get())
    }

}