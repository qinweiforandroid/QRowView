package com.qw.row.library

class ContainerDescriptor private constructor(builder: Builder) {
    val groups: ArrayList<GroupDescriptor> = builder.groups
    val topMargin = builder.topMargin

    class Builder(val groups: ArrayList<GroupDescriptor>) {
        internal var topMargin = 0

        fun setTopMargin(topMargin: Int): Builder {
            this.topMargin
            return this
        }

        fun build(): ContainerDescriptor {
            return ContainerDescriptor(this)
        }
    }
}
