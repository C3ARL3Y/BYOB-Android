package com.cearleysoftware.byob.ui.fragments.alex

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.databinding.FragmentAlexBinding
import com.cearleysoftware.byob.extensions.inflateWithBinding
import com.cearleysoftware.byob.extensions.safeActivity
import com.cearleysoftware.byob.extensions.showAlertDialog
import com.cearleysoftware.byob.ui.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.fragment_alex.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

//  Copyright © 2019 Cearley Software. All rights reserved.

class AlexFragment: Fragment() {

    private lateinit var binding: FragmentAlexBinding
    private val mainViewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = inflater.inflateWithBinding(R.layout.fragment_alex, container)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        login.setOnClickListener {
            val email = emailView.text.toString().trim()
            val password = passwordView.text.toString().trim()
            when {
                email.isBlank() -> safeActivity.showAlertDialog("Login error", "You must enter an email")
                password.isBlank() -> safeActivity.showAlertDialog("Login error", "You must enter a password")
                else -> mainViewModel.login(email, password)
            }
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_alex, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_item_sign_up -> mainViewModel.showAddEmailDialog()
        }
        return super.onOptionsItemSelected(item)
    }
}