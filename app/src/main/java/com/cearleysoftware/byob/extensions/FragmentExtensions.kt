package com.cearleysoftware.byob.extensions

import android.util.TypedValue
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

//  Copyright © 2019 Cearley Software. All rights reserved.

inline val Fragment.safeActivity: FragmentActivity
    get() = activity ?: throw IllegalStateException("Fragment not attached")

fun Fragment.getDimension(resourceId: Int): Double{
    val typedValue = TypedValue()
    resources.getValue(resourceId, typedValue, true)
    return typedValue.float.toDouble()
}