package com.cearleysoftware.byob.images

import org.koin.dsl.module.module

val imageModule = module {
    single {
        GalleryManager(get())
    }
}