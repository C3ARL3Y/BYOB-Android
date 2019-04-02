package com.cearleysoftware.byob.ui.viewmodels

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cearleysoftware.byob.database.CustomDrinkHelper
import com.cearleysoftware.byob.models.*
import com.cearleysoftware.byob.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

//  Copyright © 2019 Cearley Software. All rights reserved.

class MainViewModel: ViewModel() {

    private val disposables = CompositeDisposable()

    val navigateToImageGallery = SingleLiveEvent<(String, Uri) -> Unit>()

    val showEmailDialog = SingleLiveEvent<Unit>()

    fun navigateToImageGallery(onGalleryResult: (String, Uri) -> Unit){
        navigateToImageGallery.postValue(onGalleryResult)
    }

    fun showAddEmailDialog() {
        showEmailDialog.call()
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    data class LoginData(val email: String, val password: String)

}