package com.qw.row.demo.ext

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.qw.row.core.AbsGroupDescriptor
import com.qw.row.core.AbsGroupView
import com.qw.row.core.AbsRowDescriptor
import com.qw.row.demo.R

class CardGroupView(context: Context) : AbsGroupView<CardGroupDescriptor>(context) {
    private var mGroupView: LinearLayout

    init {
        LayoutInflater.from(context).inflate(R.layout.widget_group_card, this)
        mGroupView = findViewById(R.id.mGroupViewContainer)
    }

    override fun notifyDataChanged() {
        if (groupDescriptor.rowDescriptors.size > 0) {
            if (groupDescriptor.backgroundColor != 0) {
                mGroupView.setBackgroundColor(groupDescriptor.backgroundColor)
            }
            if (groupDescriptor.background != 0) {
                mGroupView.setBackgroundResource(groupDescriptor.background)
            }
            val params = LayoutParams(LayoutParams.MATCH_PARENT, groupDescriptor.dividerHeight)
            params.leftMargin = groupDescriptor.dividerLeftMargin
            for (i in groupDescriptor.rowDescriptors.indices) {
                val rowDescriptor = groupDescriptor.rowDescriptors[i]
                val row = createRow(rowDescriptor.getViewClass())
                row.setOnRowChangedListener(listener)
                row.initData(rowDescriptor)
                row.notifyDataChanged()
                mGroupView.addView(row)
                if (i != groupDescriptor.rowDescriptors.size - 1) {
                    val line = View(context)
                    if (groupDescriptor.dividerColor != 0) {
                        line.setBackgroundColor(groupDescriptor.dividerColor)
                    }
                    if (groupDescriptor.dividerResource != 0) {
                        line.setBackgroundResource(groupDescriptor.dividerResource)
                    }
                    mGroupView.addView(line, params)
                }
            }
        } else {
            visibility = GONE
        }
    }
}

class CardGroupDescriptor : AbsGroupDescriptor() {
    internal var background: Int = 0
    internal var backgroundColor: Int = 0
    internal val rowDescriptors = ArrayList<AbsRowDescriptor>()
    internal var dividerColor = 0
    internal var dividerResource = 0
    internal var dividerHeight = 0
    internal var dividerLeftMargin = 0

    fun addRow(descriptor: AbsRowDescriptor): CardGroupDescriptor {
        rowDescriptors.add(descriptor)
        return this
    }

    fun setBackground(@DrawableRes background: Int): CardGroupDescriptor {
        this.background = background
        return this
    }

    fun setBackgroundColor(@ColorInt backgroundColor: Int): CardGroupDescriptor {
        this.backgroundColor = backgroundColor
        return this
    }

    fun setDividerColor(color: Int): CardGroupDescriptor {
        this.dividerColor = color
        return this
    }

    fun setDividerHeight(height: Int): CardGroupDescriptor {
        this.dividerHeight = height
        return this
    }

    fun setDividerLeftMargin(dividerLeftMargin: Int): CardGroupDescriptor {
        this.dividerLeftMargin = dividerLeftMargin
        return this
    }

    fun setDividerResource(resourceId: Int): CardGroupDescriptor {
        this.dividerResource = resourceId
        return this
    }

    override fun getViewClass(): Class<*> {
        return CardGroupView::class.java
    }
}