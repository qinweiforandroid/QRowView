package com.qw.row

import com.qw.row.library.RowDescriptor

class GeneralDescriptor private constructor(builder: Builder) : RowDescriptor {
    val icon = builder.icon
    val label = builder.label
    val id = builder.id
    val background: Int = builder.background

    override fun getViewClass(): Class<GeneralRowView> {
        return GeneralRowView::class.java
    }

    internal class Builder(internal val id: Int) {
        internal var icon = 0
        internal var label: String = ""
        internal var background: Int = 0
        fun setIcon(icon: Int): Builder {
            this.icon = icon
            return this
        }

        fun setLabel(label: String): Builder {
            this.label = label
            return this
        }

        fun setBackground(background: Int): Builder {
            this.background = background
            return this
        }

        fun build(): GeneralDescriptor {
            return GeneralDescriptor(this)
        }
    }
}