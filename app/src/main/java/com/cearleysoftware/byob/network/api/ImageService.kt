package com.cearleysoftware.byob.network.api

import android.app.Application
import android.graphics.Bitmap
import android.net.Uri
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import io.reactivex.Observable
import java.io.ByteArrayOutputStream
import java.util.*

//  Copyright © 2019 Cearley Software. All rights reserved.

interface ImageService{

    fun addImage(imageURL: String): Observable<String>

}

class ImageServiceImplementation(private val storageReference: StorageReference, private val context: Application): ImageService{

    override fun addImage(imageURL: String): Observable<String> {
        return Observable.create { emitter ->
            if (imageURL.isBlank()){
                emitter.onNext("")
            }
            else {
                val bitmap = Glide.with(context).asBitmap().load(imageURL).into(500, 500).get()
                val baos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                val data = baos.toByteArray()
                val reference = storageReference.child(UUID.randomUUID().toString())

                val uploadTask = reference.putBytes(data)
                uploadTask.addOnFailureListener { error ->
                    emitter.onError(error)
                }.addOnSuccessListener {
                    uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                        if (!task.isSuccessful) {
                            task.exception?.let { error ->
                                emitter.onError(error)
                            }
                        }
                        return@Continuation reference.downloadUrl
                    }).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val downloadUri = task.result
                            emitter.onNext(downloadUri?.toString() ?: "")
                        } else {
                            emitter.onError(Throwable("Error retrieving downloadURI"))
                        }
                    }
                }
            }
        }
    }

}