package com.cearleysoftware.byob.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.cearleysoftware.byob.ui.widgets.RecyclerItemClickListener
import com.cearleysoftware.byob.ui.widgets.RecyclerItemLongClickListener
import com.cearleysoftware.byob.ui.widgets.RecyclerViewItemClickListener
import com.cearleysoftware.byob.ui.widgets.RecyclerViewItemLongClickListener

fun RecyclerView.addOnItemClick(listener: RecyclerViewItemClickListener) {
    this.addOnChildAttachStateChangeListener(RecyclerItemClickListener(this, listener))
}

fun RecyclerView.addOnItemLongClick(listener: RecyclerViewItemLongClickListener){
    this.addOnChildAttachStateChangeListener(RecyclerItemLongClickListener(this, listener))

}

@Suppress("UNCHECKED_CAST")
fun <T : View> ViewGroup.inflate(@LayoutRes layout: Int): T {
    return LayoutInflater.from(context).inflate(layout, this, false) as T
}

fun <T : ViewDataBinding> ViewGroup.inflateWithBinding(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): T {
    val layoutInflater = LayoutInflater.from(context)
    return DataBindingUtil.inflate(layoutInflater, layoutRes, this, attachToRoot) as T
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.showOrHide(show: Boolean) = if (show) show() else hide()