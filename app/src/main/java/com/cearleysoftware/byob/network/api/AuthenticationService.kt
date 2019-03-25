package com.cearleysoftware.byob.network.api

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.reactivex.Observable

//  Copyright © 2019 Cearley Software. All rights reserved.

interface AuthenticationService{

    fun getUser(): FirebaseUser?

    fun signInWithEmailAndPassword(email: String, password: String): Observable<FirebaseUser>

    fun attach(activity: Activity)

    fun detach()
}

class AuthenticationServiceImplementation(private val authentication: FirebaseAuth): AuthenticationService{

    private var activity: Activity? = null

    override fun attach(activity: Activity) {
        this.activity = activity
    }

    override fun getUser(): FirebaseUser? {
        return authentication.currentUser
    }

    override fun signInWithEmailAndPassword(email: String, password: String): Observable<FirebaseUser> {
        val activity = this.activity?: return Observable.error(Throwable("Activity is null"))

        return Observable.create { emitter ->
            authentication.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(activity) { task ->
                        if (task.isSuccessful) {
                            val user = authentication.getCurrentUser()
                            if (user != null) {
                                emitter.onNext(user)
                            } else{
                                emitter.onError(Throwable("Could not log in"))
                            }
                        } else {
                            emitter.onError(task.exception?: Throwable("Could not log in"))

                        }
                    }

        }
    }

    override fun detach() {
        this.activity = null
    }

}