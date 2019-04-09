package com.cearleysoftware.byob.ui.viewmodels

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cearleysoftware.byob.extensions.safeActivity
import com.cearleysoftware.byob.extensions.showToast
import com.cearleysoftware.byob.network.api.AuthenticationService
import com.cearleysoftware.byob.network.api.EmailService
import com.cearleysoftware.byob.util.SingleLiveEvent
import com.google.firebase.auth.FirebaseUser
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AlexViewModel(private val authenticationService: AuthenticationService,
                    private val emailService: EmailService): ViewModel() {

    val loginButtonText: LiveData<String> get() = _loginButtonText
    private val _loginButtonText = MutableLiveData<String>()

    val loginSuccess: LiveData<Boolean> get() = _loginSuccess
    private val _loginSuccess = SingleLiveEvent<Boolean>()

    val showInvalidEmailToast: LiveData<Unit> get() = _showInvalidEmailToast
    private val _showInvalidEmailToast = SingleLiveEvent<Unit>()

    val sendEmailSuccess: LiveData<Boolean> get() = _sendEmailSuccess
    private val _sendEmailSuccess = SingleLiveEvent<Boolean>()

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

    fun login(email: String, password: String) {
        disposables.add(authenticationService.signInWithEmailAndPassword(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ user ->
                    updateButtonText(user)
                    if (user != null){
                        _loginSuccess.postValue(true)
                    }
                    else{
                        _loginSuccess.postValue(false)
                    }
                }, { error ->
                    error.printStackTrace()
                    _loginSuccess.postValue(false)
                }))
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun sendEmail(email: String) {

        if (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            disposables.add(emailService.sendEmail(email)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        _sendEmailSuccess.postValue(true)
                    }, { error ->
                        _sendEmailSuccess.postValue(false)
                        error.printStackTrace()
                    }))
        }
        else{
            _showInvalidEmailToast.call()
        }
    }
}