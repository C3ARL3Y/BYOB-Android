package com.cearleysoftware.byob.ui.fragments.alex

import android.os.Bundle
import android.text.InputType
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.databinding.FragmentAlexBinding
import com.cearleysoftware.byob.extensions.*
import com.cearleysoftware.byob.ui.viewmodels.AlexViewModel
import kotlinx.android.synthetic.main.fragment_alex.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

//  Copyright © 2019 Cearley Software. All rights reserved.

class AlexFragment: Fragment() {

    private lateinit var binding: FragmentAlexBinding
    private val alexViewModel by sharedViewModel<AlexViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = inflater.inflateWithBinding(R.layout.fragment_alex, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.viewModel = alexViewModel
        binding.lifecycleOwner = this
        login.setOnClickListener {
            val email = emailView.text.toString().trim()
            val password = passwordView.text.toString().trim()
            when {
                email.isBlank() -> safeActivity.showAlertDialog("Login error", "You must enter an email")
                password.isBlank() -> safeActivity.showAlertDialog("Login error", "You must enter a password")
                else -> alexViewModel.login(email, password)
            }
        }

        alexViewModel.showInvalidEmailToast.observe(this, Observer {
            safeActivity.showToast("You must enter a valid email.")
        })

        alexViewModel.sendEmailSuccess.observe(this, Observer { success ->
            if (success) {
                safeActivity.showToast("Email sent")
            }
            else{
                safeActivity.showToast("Error sending email")

            }
        })
        alexViewModel.loginSuccess.observe(this, Observer { success ->
            if (success){
                safeActivity.showToast("Login successful")
            }
            else{
                safeActivity.showToast("Login failed")
            }
        })
        alexViewModel.checkForLoggedInUser()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_alex, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_item_sign_up -> showEmailDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showEmailDialog() {
        val alertBuilder = android.app.AlertDialog.Builder(safeActivity)
        val edittext = EditText(safeActivity)
        edittext.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        edittext.requestFocus()
        val padding = resources.displayMetrics.dpToPx(15)
        edittext.setPadding(padding,padding,padding,padding)
        alertBuilder.setTitle("Enter email")

        alertBuilder.setView(edittext)

        alertBuilder.setPositiveButton("Ok") { _, _ ->
            val email = edittext.text.toString().trim()
            alexViewModel.sendEmail(email)
        }
        val alert =alertBuilder.create()
        alert.show()

        edittext.setOnEditorActionListener { view, actionId, event ->

            if (actionId == EditorInfo.IME_ACTION_DONE){
                val email = view.text.toString().trim()
                alexViewModel.sendEmail(email)
                alert.cancel()
            }
            false
        }
    }
}