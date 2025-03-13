package com.qw.row.item

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import com.qw.row.R
import com.qw.row.core.AbsRowDescriptor
import com.qw.row.core.AbsRowView

/**
 * author : qinwei@agora.io
 * date : 2025/3/13 11:26
 * description :
 */
class SwitchRowView(context: Context) : AbsRowView<SwitchDescriptor>(context) {

    private var mWidgetRowIconImg: ImageView
    private var mWidgetRowLabel: TextView
    private var mWidgetSwitch: Switch

    init {
        val padding = context.resources.getDimension(R.dimen.widget_general_row_padding).toInt()
        setPadding(padding, padding, padding, padding)
        LayoutInflater.from(context).inflate(R.layout.widget_row_switch, this)
        setBackgroundResource(R.drawable.widgets_general_row_select)
        mWidgetRowIconImg = findViewById(R.id.mWidgetRowIconImg)
        mWidgetRowLabel = findViewById(R.id.mWidgetRowLabel)
        mWidgetSwitch = findViewById(R.id.mWidgetSwitch)
    }

    override fun notifyDataChanged() {
        mWidgetSwitch.isChecked = descriptor.checked
        if (descriptor.icon == 0) {
            mWidgetRowIconImg.visibility = GONE
        } else {
            mWidgetRowIconImg.setBackgroundResource(descriptor.icon)
        }
        mWidgetRowLabel.text = descriptor.title
        mWidgetSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            descriptor.checked = isChecked
            listener?.onRowChanged(this)
        }
    }

    val isChecked: Boolean
        get() {
            return descriptor.checked
        }
}

class SwitchDescriptor(id: Int = 0, var icon: Int = 0, val title: String, var checked: Boolean = false) :
    AbsRowDescriptor(id) {
    override fun getViewClass(): Class<*> {
        return SwitchRowView::class.java
    }
}