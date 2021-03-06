package com.cearleysoftware.byob.ui.widgets

import android.view.View
import androidx.recyclerview.widget.RecyclerView

//  Copyright © 2019 Cearley Software. All rights reserved.

typealias RecyclerViewItemLongClickListener = (position: Int, view: View) -> Unit

class RecyclerItemLongClickListener(
        private val mRecycler: RecyclerView,
        private val longClickListener: RecyclerViewItemLongClickListener? = null
) : RecyclerView.OnChildAttachStateChangeListener {

    override fun onChildViewDetachedFromWindow(view: View) {
        view.setOnLongClickListener(null)
    }

    override fun onChildViewAttachedToWindow(view: View) {
        view.setOnLongClickListener {
            setOnChildAttachedToWindow(view)
            true
        }
    }

    private fun setOnChildAttachedToWindow(v: View?) {
        if (v != null) {
            val position = mRecycler.getChildLayoutPosition(v)
            if (position >= 0) {
                longClickListener?.invoke(position, v)
            }
        }
    }
}