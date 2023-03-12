package com.qw.row.library

interface RowDescriptor {
    fun getViewClass(): Class<out AbsRow>
}