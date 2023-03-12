package com.qw.row.library

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

abstract class AbsRow : ConstraintLayout {
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
    abstract fun initData(rowDescriptor: RowDescriptor, listener: OnRowClickListener?)

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