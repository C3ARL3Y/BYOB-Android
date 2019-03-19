package com.cearleysoftware.byob.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.databinding.MainActivityBinding
import com.cearleysoftware.byob.extensions.replaceFragment
import com.cearleysoftware.byob.extensions.setDataBindingContentView
import com.cearleysoftware.byob.ui.fragments.MainFragment

//  Copyright © 2019 Cearley Software. All rights reserved.

class MainActivity : AppCompatActivity() {

    private var binding: MainActivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setDataBindingContentView(R.layout.main_activity)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setupUI()
    }

    private fun setupUI() {
        replaceFragment(fragment = MainFragment())

    }
}
