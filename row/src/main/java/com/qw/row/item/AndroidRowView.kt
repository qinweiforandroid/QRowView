package com.qw.row.item

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.qw.row.R
import com.qw.row.core.AbsRowDescriptor
import com.qw.row.core.AbsRowView

class AndroidRowView(context: Context) : AbsRowView<AndroidDescriptor>(context), View.OnClickListener {
    private var mRowIconImg: ImageView
    private var mRowLabel: TextView

    init {
        val padding = context.resources.getDimension(R.dimen.widget_general_row_padding).toInt()
        setPadding(padding, padding, padding, padding)
        LayoutInflater.from(context).inflate(R.layout.widget_row_android, this)
        mRowIconImg = findViewById(R.id.mRowIconImg)
        mRowLabel = findViewById(R.id.mRowLabel)
    }

    override fun onClick(v: View) {
        listener?.onRowChanged(this)
    }

    override fun notifyDataChanged() {
        if (descriptor.icon == 0) {
            mRowIconImg.visibility = GONE
        } else {
            mRowIconImg.setBackgroundResource(descriptor.icon)
        }
        mRowLabel.text = descriptor.label
        if (descriptor.rowId != 0) {
            setOnClickListener(this)
            setBackgroundResource(R.drawable.widgets_general_row_select)
        }
    }
}

class AndroidDescriptor(id: Int = 0, var icon: Int = 0, var label: String = "") :
    AbsRowDescriptor(id) {
    override fun getViewClass(): Class<*> {
        return AndroidRowView::class.java
    }
}