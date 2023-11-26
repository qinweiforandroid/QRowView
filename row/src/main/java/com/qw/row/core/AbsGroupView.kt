package com.qw.row.core

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.qw.row.group.GroupDescriptor

abstract class AbsGroupView : LinearLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    abstract fun initData(groupDescriptor: IGroupDescriptor, listener: OnRowClickListener? = null)

    abstract fun notifyDataChanged()

    fun createRow(clazz: Class<out AbsRowView>): AbsRowView {
        val constructor = clazz.getDeclaredConstructor(Context::class.java)
        constructor.isAccessible = true
        return constructor.newInstance(context) as AbsRowView
    }
}

interface IGroupDescriptor {
    fun getViewClass(): Class<out AbsGroupView>
}