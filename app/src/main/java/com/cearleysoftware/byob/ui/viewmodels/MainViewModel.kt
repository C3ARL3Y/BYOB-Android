package com.cearleysoftware.byob.ui.viewmodels

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.cearleysoftware.byob.util.SingleLiveEvent

//  Copyright © 2019 Cearley Software. All rights reserved.

class MainViewModel: ViewModel() {

    val navigateToImageGallery = SingleLiveEvent<(String, Uri) -> Unit>()

    fun navigateToImageGallery(onGalleryResult: (String, Uri) -> Unit){
        navigateToImageGallery.postValue(onGalleryResult)
    }

}