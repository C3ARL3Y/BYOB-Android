package com.cearleysoftware.byob.network.api

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import io.reactivex.Observable

interface EmailService{

    fun sendEmail(email: String): Observable<Boolean>
}

class EmailServiceImplementation(private val database: DatabaseReference): EmailService {

    override fun sendEmail(email: String): Observable<Boolean> {
        return Observable.create { emitter ->
            database.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    emitter.onError(error.toException())
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val count = dataSnapshot.childrenCount.toInt()
                    val index = if (count == 0) count else count + 1
                    database.child(index.toString()).setValue(email)
                            .addOnSuccessListener {
                                emitter.onNext(true)
                            }
                            .addOnFailureListener { error ->
                                emitter.onError(error)
                            }
                }
            })

        }
    }
}