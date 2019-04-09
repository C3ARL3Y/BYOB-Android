package com.cearleysoftware.byob.ui.viewmodels

import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

//  Copyright © 2019 Cearley Software. All rights reserved.

val viewModelsModule = module {
    viewModel {
        MainViewModel()
    }

    viewModel {
        CreateDrinkViewModel(get())
    }

    viewModel {
        FavoritesViewModel(get())
    }

    viewModel {
        SearchViewModel(get())
    }

    viewModel {
        CustomDrinkViewModel(get())
    }

    viewModel {
        AlexViewModel(get(), get())
    }
}