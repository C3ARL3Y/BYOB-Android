package com.cearleysoftware.byob.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

//  Copyright © 2019 Cearley Software. All rights reserved.

inline val Fragment.safeActivity: FragmentActivity
    get() = activity ?: throw IllegalStateException("Fragment not attached")