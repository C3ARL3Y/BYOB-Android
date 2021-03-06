package com.cearleysoftware.byob.extensions

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.cearleysoftware.byob.R
import kotlin.math.roundToInt

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

fun Activity?.popAllInBackStack(){
    val compatActivity = this as? AppCompatActivity ?: return
    compatActivity.supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
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

fun Activity.showAlertDialog(title: String = "", message: String = ""){
    AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Ok", null)
            .create()
            .show()
}

fun Activity.showToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun DisplayMetrics.dpToPx(dp: Int): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), this).roundToInt()
}