package com.qw.row.library

class GroupDescriptor private constructor(builder: Builder) {
    val title = builder.title
    val dividerColor = builder.dividerColor
    val dividerLeftMargin = builder.dividerLeftMargin
    val dividerResource = builder.dividerResource
    val dividerHeight = builder.dividerHeight
    val rowDescriptors = builder.rowDescriptors


    fun add(descriptor: RowDescriptor): GroupDescriptor {
        rowDescriptors.add(descriptor)
        return this
    }

    class Builder(internal val title: String) {
        internal val rowDescriptors = ArrayList<RowDescriptor>()
        internal var dividerColor = 0
        internal var dividerResource = 0
        internal var dividerHeight = 0
        internal var dividerLeftMargin = 0
        fun addRow(descriptor: RowDescriptor): Builder {
            rowDescriptors.add(descriptor)
            return this
        }

        fun setDividerColor(color: Int): Builder {
            this.dividerColor = color
            return this
        }

        fun setDividerHeight(height: Int): Builder {
            this.dividerHeight = height
            return this
        }

        fun setDividerLeftMargin(dividerLeftMargin: Int): Builder {
            this.dividerLeftMargin = dividerLeftMargin
            return this
        }

        fun setDividerResource(resourceId: Int): Builder {
            this.dividerResource = resourceId
            return this
        }

        fun build(): GroupDescriptor {
            return GroupDescriptor(this)
        }
    }
}