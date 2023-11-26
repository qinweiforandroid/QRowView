package com.qw.row.core

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

abstract class AbsRowView : ConstraintLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    /**
     * @param rowDescriptor
     * @param listener
     */
    abstract fun initData(rowDescriptor: IRowDescriptor, listener: OnRowClickListener?)

    /**
     * 更新ui
     */
    abstract fun notifyDataChanged()

    /**
     * 获取id
     *
     * @return
     */
    abstract fun getRowId(): Int
}

interface IRowDescriptor {
    fun getViewClass(): Class<out AbsRowView>
}

interface OnRowClickListener {
    /**
     * row被点击
     *
     * @param rowView
     */
    fun onRowClick(rowView: AbsRowView)
}