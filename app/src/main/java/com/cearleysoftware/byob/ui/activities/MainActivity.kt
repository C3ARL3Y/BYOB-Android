package com.cearleysoftware.byob.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.constants.Constants
import com.cearleysoftware.byob.databinding.MainActivityBinding
import com.cearleysoftware.byob.extensions.*
import com.cearleysoftware.byob.images.GalleryManager
import com.cearleysoftware.byob.network.api.AuthenticationService
import com.cearleysoftware.byob.ui.fragments.MainFragment
import com.cearleysoftware.byob.ui.viewmodels.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

//  Copyright © 2019 Cearley Software. All rights reserved.

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()
    private val galleryManager by inject<GalleryManager>()
    private val authenticationService by inject<AuthenticationService>()

    private var binding: MainActivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setDataBindingContentView(R.layout.main_activity)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        authenticationService.attach(this)
        setupUI()
    }

    private fun setupUI() {

        viewModel.navigateToImageGallery.observe(this, Observer { onResult ->
            galleryManager.startGallery(this, onResult)
        })

        replaceFragment(fragment = MainFragment())
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
        authenticationService.detach()
    }
}
