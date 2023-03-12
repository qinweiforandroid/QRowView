package com.qw.row.library

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class GroupView : LinearLayout {
    private lateinit var group: GroupDescriptor
    private var listener: OnRowClickListener? = null
    private var titleContainer: View
    private var mGroupViewTitleLabel: TextView
    private var mGroupView: LinearLayout

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    init {
        LayoutInflater.from(context).inflate(R.layout.widget_row_group_general, this)
        titleContainer = findViewById(R.id.mGroupViewTitleLayout)
        mGroupViewTitleLabel = findViewById(R.id.mGroupViewTitleLabel)
        mGroupView = findViewById(R.id.mGroupViewContainer)
    }

    fun initData(groupDescriptor: GroupDescriptor, listener: OnRowClickListener? = null) {
        this.listener = listener
        this.group = groupDescriptor
    }

    fun notifyDataChanged() {
        if (group.rowDescriptors.size > 0) {
            if (TextUtils.isEmpty(group.title)) {
                titleContainer.visibility = GONE
            } else {
                mGroupViewTitleLabel.text = group.title
            }
            val params = LayoutParams(LayoutParams.MATCH_PARENT, group.dividerHeight)
            params.leftMargin = group.dividerLeftMargin
            for (i in group.rowDescriptors.indices) {
                val rowDescriptor = group.rowDescriptors[i]
                val row = create(context, rowDescriptor.getViewClass())
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

    private fun create(context: Context, clazz: Class<out AbsRow>): AbsRow {
        val constructor = clazz.getDeclaredConstructor(Context::class.java)
        constructor.isAccessible = true
        return constructor.newInstance(context) as AbsRow
    }
}