package com.cearleysoftware.byob.ui.activities

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
import com.cearleysoftware.byob.ui.fragments.picks.*
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

        viewModel.login.observe(this, Observer { loginData ->
            signIn(loginData)
        })

        viewModel.showEmailDialog.observe(this, Observer {
            showEmailDialog()
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

        viewModel.showToast.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        })

        viewModel.showAlertDialog.observe(this, Observer { alertData ->
            AlertDialog.Builder(this)
                    .setTitle(alertData.title)
                    .setMessage(alertData.message)
                    .setPositiveButton("Ok", null)
                    .create()
                    .show()
        })

        viewModel.popBackStack.observe(this, Observer {
            popBackStack()
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
        alert.create()
        alert.show()
    }

    private fun signIn(loginData: MainViewModel.LoginData?) { // todo: Move to ViewModel in AlexFragment
        loginData?: return
        disposables.add(authenticationService.signInWithEmailAndPassword(loginData.email, loginData.password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ user ->
                    if (user != null){
                        Toast.makeText(this, "Login successful", Toast.LENGTH_LONG).show()
                    }
                }, { error ->
                    error.printStackTrace()
                    Toast.makeText(this, "Login failed", Toast.LENGTH_LONG).show()

                }))
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
