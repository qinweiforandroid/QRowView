package com.qw.row.item

import com.qw.row.core.AbsRowView
import com.qw.row.core.IRowDescriptor

class AndroidDescriptor(var id: Int = 0, var icon: Int = 0, var label: String = "") :
    IRowDescriptor {
    override fun getViewClass(): Class<out AbsRowView?> {
        return AndroidRowView::class.java
    }
}