package com.cearleysoftware.byob.ui.viewmodels

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cearleysoftware.byob.extensions.showAlertDialog
import com.cearleysoftware.byob.extensions.showToast
import com.cearleysoftware.byob.network.api.AuthenticationService
import com.google.firebase.auth.FirebaseUser
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AlexViewModel(
        private val context: Context,
        private val authenticationService: AuthenticationService
): ViewModel() {

    val loginButtonText: LiveData<String> get() = _loginButtonText
    private val _loginButtonText = MutableLiveData<String>()

    private val disposables = CompositeDisposable()

    fun checkForLoggedInUser(){
        updateButtonText(authenticationService.getUser())
    }

    private fun updateButtonText(user: FirebaseUser?){
        if (user != null){
            _loginButtonText.postValue("Already Logged In")
        }
        else{
            _loginButtonText.postValue("Login")
        }
    }

    private fun login(email: String, password: String) {
        context as Activity
        disposables.add(authenticationService.signInWithEmailAndPassword(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ user ->
                    updateButtonText(user)
                    if (user != null){
                        context.showToast("Login successful")
                    }
                    else{
                        context.showToast("Login failed")
                    }
                }, { error ->
                    error.printStackTrace()
                    context.showToast("Login failed")
                }))
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun onLoginClicked(email: String, password: String) {
        context as Activity
        when {
            email.isBlank() -> context.showAlertDialog("Login error", "You must enter an email")
            password.isBlank() -> context.showAlertDialog("Login error", "You must enter a password")
            else -> login(email, password)
        }
    }
}