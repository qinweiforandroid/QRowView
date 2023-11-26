package com.qw.row.item

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.qw.row.core.AbsRowView
import com.qw.row.core.IRowDescriptor
import com.qw.row.core.OnRowClickListener
import com.qw.row.library.R

class AndroidRowView : AbsRowView, View.OnClickListener {
    private var mRowIconImg: ImageView
    private var mRowLabel: TextView
    private lateinit var descriptor: AndroidDescriptor
    private var listener: OnRowClickListener? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    init {
        val padding = context.resources.getDimension(R.dimen.widget_general_row_padding).toInt()
        setPadding(padding, padding, padding, padding)
        LayoutInflater.from(context).inflate(R.layout.widget_row_android, this)
        mRowIconImg = findViewById(R.id.mRowIconImg)
        mRowLabel = findViewById(R.id.mRowLabel)
    }

    override fun onClick(v: View) {
        listener?.onRowClick(this)
    }

    override fun notifyDataChanged() {
        if (descriptor.icon == 0) {
            mRowIconImg.visibility = GONE
        } else {
            mRowIconImg.setBackgroundResource(descriptor.icon)
        }
        mRowLabel.text = descriptor.label
        if (descriptor.id != 0) {
            setOnClickListener(this)
            setBackgroundResource(R.drawable.widgets_general_row_select)
        }
    }

    override fun getRowId(): Int {
        return descriptor.id
    }

    override fun initData(rowDescriptor: IRowDescriptor, listener: OnRowClickListener?) {
        if (rowDescriptor is AndroidDescriptor) {
            this.listener = listener
            descriptor = rowDescriptor
        } else {
            throw IllegalArgumentException("GeneralRowDescriptor can use")
        }
    }
}