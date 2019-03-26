package com.cearleysoftware.byob.images

import org.koin.dsl.module.module

//  Copyright © 2019 Cearley Software. All rights reserved.

val imageModule = module {
    single {
        GalleryManager(get())
    }
}