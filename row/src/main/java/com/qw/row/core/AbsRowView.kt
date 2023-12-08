package com.qw.row.core

import android.content.Context
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout

abstract class AbsRowView<T : AbsRowDescriptor> : ConstraintLayout {
    protected var listener: OnRowChangedListener? = null
    protected lateinit var descriptor: T

    constructor(context: Context) : super(context)

    fun setOnRowChangedListener(listener: OnRowChangedListener?) {
        this.listener = listener
    }

    /**
     * @param rowDescriptor
     */
    fun initData(rowDescriptor: T) {
        this.descriptor = rowDescriptor
        id = this.descriptor.rowId
    }

    fun notifyDataChanged(rowDescriptor: T) {
        initData(rowDescriptor)
        notifyDataChanged()
    }

    /**
     * 更新ui
     */
    abstract fun notifyDataChanged()
}

abstract class AbsRowDescriptor constructor(var rowId: Int = View.NO_ID) {
    abstract fun getViewClass(): Class<*>
}

interface OnRowChangedListener {
    /**
     *
     * @param rowId
     */
    fun onRowChanged(rowView: AbsRowView<*>)
}