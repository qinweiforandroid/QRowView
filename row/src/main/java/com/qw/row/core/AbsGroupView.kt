package com.qw.row.core

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

abstract class AbsGroupView<T : AbsGroupDescriptor> : LinearLayout {

    protected lateinit var groupDescriptor: T
    protected var listener: OnRowChangedListener? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)


    fun initData(groupDescriptor: T, listener: OnRowChangedListener?) {
        this.groupDescriptor = groupDescriptor
        this.listener = listener
    }

    abstract fun notifyDataChanged()

    fun createRow(clazz: Class<*>): AbsRowView<AbsRowDescriptor> {
        val constructor = clazz.getDeclaredConstructor(Context::class.java)
        constructor.isAccessible = true
        return constructor.newInstance(context) as AbsRowView<AbsRowDescriptor>
    }
}

abstract class AbsGroupDescriptor {
    abstract fun getViewClass(): Class<*>
}