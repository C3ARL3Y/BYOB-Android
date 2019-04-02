package com.cearleysoftware.byob.ui.activities

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.constants.Constants
import com.cearleysoftware.byob.databinding.MainActivityBinding
import com.cearleysoftware.byob.extensions.*
import com.cearleysoftware.byob.images.GalleryManager
import com.cearleysoftware.byob.network.api.AuthenticationService
import com.cearleysoftware.byob.network.api.EmailService
import com.cearleysoftware.byob.ui.fragments.MainFragment
import com.cearleysoftware.byob.ui.viewmodels.MainViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

//  Copyright © 2019 Cearley Software. All rights reserved.

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()
    private val galleryManager by inject<GalleryManager>()
    private val authenticationService by inject<AuthenticationService>()
    private val emailService by inject<EmailService>()
    private val disposables = CompositeDisposable()

    private var binding: MainActivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setDataBindingContentView(R.layout.main_activity)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        authenticationService.attach(this)
        setupUI()
    }

    private fun setupUI() {

        viewModel.showEmailDialog.observe(this, Observer {
            showEmailDialog()
        })

        viewModel.navigateToImageGallery.observe(this, Observer { onResult ->
            galleryManager.startGallery(this, onResult)
        })

        replaceFragment(fragment = MainFragment())


    }

    private fun showEmailDialog() {
        val alert = android.app.AlertDialog.Builder(this)
        val edittext = EditText(this)
        edittext.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        edittext.requestFocus()
        val padding = resources.displayMetrics.dpToPx(15)
        edittext.setPadding(padding,padding,padding,padding)
        alert.setTitle("Enter email")

        alert.setView(edittext)

        alert.setPositiveButton("Ok") { _, _ ->
            val email = edittext.text.toString().trim()
            if (email.isNotBlank()) {
                disposables.add(emailService.sendEmail(email)// todo: Move to ViewModel in AlexFragment
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            Toast.makeText(this, "Email sent", Toast.LENGTH_LONG).show()
                        }, { error ->
                            error.printStackTrace()
                            Toast.makeText(this, "Error sending email", Toast.LENGTH_LONG).show()
                        }))
            }
            else{
                showToast("You must enter a valid email.")
            }
        }
        alert.create()
        alert.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){

            // todo: Implement camera
           /* Constants.CAMERA_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    CameraManager.onActivityResult()
                }
            }*/
            Constants.GALLERY_REQUEST_CODE -> {
                galleryManager.onActivityResult(resultCode, data)

            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            Constants.READ_EXTERNAL_STORAGE_REQUEST_CODE -> {
                galleryManager.onRequestPermissionsResult(grantResults)
            }

            // todo: Implement camera
           /* Constants.CAMERA_PERMISSION_REQUEST_CODE -> {
                CameraManager.onRequestPermissionsResult(grantResults)
            }*/

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        galleryManager.onDestroy()
        disposables.clear()
        authenticationService.detach()
    }
}
