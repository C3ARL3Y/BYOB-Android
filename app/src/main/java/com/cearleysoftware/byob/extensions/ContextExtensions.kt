package com.cearleysoftware.byob.extensions

import android.content.Context
import android.util.TypedValue

fun Context.getDimension(resourceId: Int): Double{
    val typedValue = TypedValue()
    resources.getValue(resourceId, typedValue, true)
    return typedValue.float.toDouble()
}