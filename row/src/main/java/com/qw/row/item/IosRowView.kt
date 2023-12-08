package com.qw.row.item

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.qw.row.R
import com.qw.row.core.AbsRowDescriptor
import com.qw.row.core.AbsRowView

class IosRowView(context: Context) : AbsRowView<IosDescriptor>(context), View.OnClickListener {
    private var mWidgetRowActionImg: ImageView
    private var mWidgetRowIconImg: ImageView
    private var mWidgetRowLabel: TextView
    private var mWidgetRowValueLabel: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.widget_row_ios, this)
        val padding = context.resources.getDimension(R.dimen.widget_general_row_padding).toInt()
        setPadding(padding, padding, padding, padding)
        mWidgetRowIconImg = findViewById<View>(R.id.mWidgetRowIconImg) as ImageView
        mWidgetRowLabel = findViewById<View>(R.id.mWidgetRowLabel) as TextView
        mWidgetRowValueLabel = findViewById<View>(R.id.mWidgetRowValueLabel) as TextView
        mWidgetRowActionImg = findViewById<View>(R.id.mWidgetRowActionImg) as ImageView
    }

    override fun onClick(v: View) {
        listener?.onRowChanged(this)
    }

    override fun notifyDataChanged() {
        if (descriptor.icon == 0) {
            mWidgetRowIconImg.visibility = GONE
        } else {
            mWidgetRowIconImg.setBackgroundResource(descriptor.icon)
        }
        mWidgetRowLabel.text = descriptor.label
        mWidgetRowValueLabel.text = descriptor.getValue()
        if (descriptor.rowId > 0) {
            setBackgroundResource(R.drawable.widgets_general_row_select)
            mWidgetRowActionImg.setBackgroundResource(R.drawable.action_row)
            mWidgetRowActionImg.visibility = VISIBLE
            setOnClickListener(this)
        } else {
            mWidgetRowActionImg.visibility = GONE
        }
    }
}

class IosDescriptor(id: Int = 0, var icon: Int = 0, var label: String = "") : AbsRowDescriptor(id) {
    private var value: String = ""

    fun setValue(value: String): IosDescriptor {
        this.value = value
        return this
    }

    fun getValue(): String {
        return value
    }

    override fun getViewClass(): Class<*> {
        return IosRowView::class.java
    }
}