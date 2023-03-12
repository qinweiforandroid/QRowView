package com.qw.row

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.qw.row.library.AbsRow
import com.qw.row.library.OnRowClickListener
import com.qw.row.library.RowDescriptor

class GeneralRowView(context: Context) : AbsRow(context), View.OnClickListener {
    private var mRowIconImg: ImageView
    private var mRowLabel: TextView
    private lateinit var descriptor: GeneralDescriptor
    private var listener: OnRowClickListener? = null

    init {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_row_android, this)
        mRowIconImg = findViewById(R.id.mRowIconImg)
        mRowLabel = findViewById(R.id.mRowLabel)
    }

    override fun onClick(v: View) {
        if (listener != null) {
            listener?.onRowClick(this)
        }
    }

    override fun notifyDataChanged() {
        if (descriptor.icon == 0) {
            mRowIconImg.visibility = GONE
        } else {
            mRowIconImg.setImageResource(descriptor.icon)
        }
        mRowLabel.text = descriptor.label
        if (descriptor.id != 0) {
            setOnClickListener(this)
        }
        if (descriptor.background != 0) {
            setBackgroundColor(descriptor.background)
        }
    }

    override fun initData(rowDescriptor: RowDescriptor, listener: OnRowClickListener?) {
        if (rowDescriptor is GeneralDescriptor) {
            this.listener = listener
            descriptor = rowDescriptor
        } else {
            throw IllegalArgumentException("GeneralRowDescriptor can use")
        }
    }

    override fun getRowId(): Int {
        return descriptor.id
    }
}