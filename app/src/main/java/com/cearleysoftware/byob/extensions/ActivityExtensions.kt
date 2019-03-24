package com.cearleysoftware.byob.extensions

import android.app.Activity
import android.content.pm.PackageManager
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.cearleysoftware.byob.R

//  Copyright © 2019 Cearley Software. All rights reserved.

fun <T : ViewDataBinding> Activity.setDataBindingContentView(@LayoutRes res: Int): T {
    return DataBindingUtil.setContentView(this, res)
}

fun Activity?.addFragment(
        @IdRes id: Int = R.id.container,
        fragment: Fragment,
        tag: String? = null,
        addToBackStack: Boolean = true
) {
    val compatActivity = this as? AppCompatActivity ?: return
    compatActivity.supportFragmentManager.beginTransaction()
            .apply {
                add(id, fragment, tag)
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                if (addToBackStack) {
                    addToBackStack(null)
                }
                commit()
            }
}

fun Activity?.replaceFragment(
        @IdRes id: Int = R.id.container,
        fragment: Fragment,
        tag: String? = null,
        addToBackStack: Boolean = false
) {
    val compatActivity = this as? AppCompatActivity ?: return
    compatActivity.supportFragmentManager.beginTransaction()
            .apply {
                replace(id, fragment, tag)
                if (addToBackStack) {
                    addToBackStack(null)
                }
                commit()
            }
}

fun Activity?.popBackStack(){
    val compatActivity = this as? AppCompatActivity ?: return
    compatActivity.supportFragmentManager.popBackStack()
}

fun Activity?.isPermissionGranted(permission: String): Boolean{
    val appCompatActivity = this as? AppCompatActivity?: return false
    return ActivityCompat.checkSelfPermission(appCompatActivity, permission) == PackageManager.PERMISSION_GRANTED
}

fun Activity.requestPermission(permission: String,
                                        requestId: Int) {
    val appCompatActivity = this as? AppCompatActivity?: return
    ActivityCompat.requestPermissions(appCompatActivity, arrayOf(permission), requestId)

}
