package com.cearleysoftware.byob.ui.viewmodels

import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelsModule = module {
    viewModel {
        MainViewModel()
    }
}