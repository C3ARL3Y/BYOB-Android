package com.cearleysoftware.byob.ui.activities

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.constants.Constants
import com.cearleysoftware.byob.databinding.MainActivityBinding
import com.cearleysoftware.byob.extensions.*
import com.cearleysoftware.byob.images.GalleryManager
import com.cearleysoftware.byob.ui.fragments.MainFragment
import com.cearleysoftware.byob.ui.fragments.picks.*
import com.cearleysoftware.byob.ui.viewmodels.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

//  Copyright © 2019 Cearley Software. All rights reserved.

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()
    private val galleryManager by inject<GalleryManager>()

    private var binding: MainActivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setDataBindingContentView(R.layout.main_activity)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setupUI()
    }

    private fun setupUI() {
        viewModel.navigateToViewDrinks.observe(this, Observer { drinkType ->
            replaceFragment(fragment = ViewDrinksFragment.newInstance(drinkType), addToBackStack = true)
        })

        viewModel.navigateToDrinkFromPicks.observe(this, Observer { drink ->
            replaceFragment(fragment = DrinkFragment.newInstance(drink), addToBackStack = true)
        })

        viewModel.navigateToCreateDrink.observe(this, Observer { drink ->
            replaceFragment(fragment = CreateDrinkFragment.newInstance(drink), addToBackStack = true)
        })

        viewModel.navigateToSteps.observe(this, Observer { steps ->
            replaceFragment(fragment = StepsFragment.newInstance(steps), addToBackStack = true)

        })

        viewModel.navigateToNutrients.observe(this, Observer { nutrients ->
            replaceFragment(fragment = NutrientsFragment.newInstance(nutrients), addToBackStack = true)

        })

        viewModel.navigateToImageGallery.observe(this, Observer { onResult ->
            galleryManager.startGallery(this, onResult)
        })

        viewModel.navigateToFavoriteDrink.observe(this, Observer { drinkType ->
            Log.d("favoriteDrink", "favoriteDrink")

        })

        viewModel.navigateToCoffeeBase.observe(this, Observer { drinkType ->
            Log.d("coffeeBase", "coffeebase")

        })

        viewModel.showAlertDialog.observe(this, Observer { alertData ->
            AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setTitle(alertData.title)
                    .setMessage(alertData.message)
                    .create()
                    .show()
        })

        viewModel.popBackStack.observe(this, Observer {
            popBackStack()
        })
        replaceFragment(fragment = MainFragment())

    }

    fun startGallery(){

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){


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

           /* Constants.CAMERA_PERMISSION_REQUEST_CODE -> {
                CameraManager.onRequestPermissionsResult(grantResults)
            }*/

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        galleryManager.onDestroy()
    }
}
