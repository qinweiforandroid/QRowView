package com.qw.row.group

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.qw.row.core.AbsGroupView
import com.qw.row.core.IGroupDescriptor
import com.qw.row.core.IRowDescriptor
import com.qw.row.core.OnRowClickListener
import com.qw.row.library.R

class GroupView : AbsGroupView {
    private lateinit var group: GroupDescriptor
    private var listener: OnRowClickListener? = null
    private var mGroupView: LinearLayout

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    init {
        LayoutInflater.from(context).inflate(R.layout.widget_row_group_general, this)
        mGroupView = findViewById(R.id.mGroupViewContainer)
    }

    override fun initData(groupDescriptor: IGroupDescriptor, listener: OnRowClickListener?) {
        this.group = groupDescriptor as GroupDescriptor
        this.listener = listener
    }


    override fun notifyDataChanged() {
        if (group.rowDescriptors.size > 0) {
            if (group.backgroundColor != 0) {
                mGroupView.setBackgroundColor(group.backgroundColor)
            }
            if (group.background != 0) {
                mGroupView.setBackgroundResource(group.background)
            }
            val params = LayoutParams(LayoutParams.MATCH_PARENT, group.dividerHeight)
            params.leftMargin = group.dividerLeftMargin
            for (i in group.rowDescriptors.indices) {
                val rowDescriptor = group.rowDescriptors[i]
                val row = createRow(rowDescriptor.getViewClass())
                row.initData(rowDescriptor, listener)
                row.notifyDataChanged()
                mGroupView.addView(row)
                if (i != group.rowDescriptors.size - 1) {
                    val line = View(context)
                    if (group.dividerColor != 0) {
                        line.setBackgroundColor(group.dividerColor)
                    }
                    if (group.dividerResource != 0) {
                        line.setBackgroundResource(group.dividerResource)
                    }
                    mGroupView.addView(line, params)
                }
            }
        } else {
            visibility = GONE
        }
    }
}

class GroupDescriptor private constructor(builder: Builder) : IGroupDescriptor {
    val dividerColor = builder.dividerColor
    val dividerLeftMargin = builder.dividerLeftMargin
    val dividerResource = builder.dividerResource
    val dividerHeight = builder.dividerHeight
    val rowDescriptors = builder.rowDescriptors
    val background = builder.background
    val backgroundColor = builder.backgroundColor

    fun addRow(descriptor: IRowDescriptor): GroupDescriptor {
        rowDescriptors.add(descriptor)
        return this
    }

    class Builder {
        internal var background: Int = 0
        internal var backgroundColor: Int = 0
        internal val rowDescriptors = ArrayList<IRowDescriptor>()
        internal var dividerColor = 0
        internal var dividerResource = 0
        internal var dividerHeight = 0
        internal var dividerLeftMargin = 0
        fun addRow(descriptor: IRowDescriptor): Builder {
            rowDescriptors.add(descriptor)
            return this
        }

        fun setBackground(@DrawableRes background: Int): Builder {
            this.background = background
            return this
        }

        fun setBackgroundColor(@ColorInt backgroundColor: Int): Builder {
            this.backgroundColor = backgroundColor
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

        fun builder(): GroupDescriptor {
            return GroupDescriptor(this)
        }
    }

    override fun getViewClass(): Class<out AbsGroupView> {
        return GroupView::class.java
    }
}