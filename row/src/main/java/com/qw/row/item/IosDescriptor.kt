package com.qw.row.item

import com.qw.row.core.AbsRowView
import com.qw.row.core.IRowDescriptor
import com.qw.row.item.IosRowView

class IosDescriptor(var id: Int = 0, var icon: Int = 0, var label: String = "") : IRowDescriptor {
    private var value: String = ""

    fun setValue(value: String): IosDescriptor {
        this.value = value
        return this
    }

    fun getValue(): String {
        return value
    }

    override fun getViewClass(): Class<out AbsRowView?> {
        return IosRowView::class.java
    }
}