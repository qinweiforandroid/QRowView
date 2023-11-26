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

class IosRowView : AbsRowView, View.OnClickListener {
    private var mWidgetRowActionImg: ImageView
    private var mWidgetRowIconImg: ImageView
    private var mWidgetRowLabel: TextView
    private var listener: OnRowClickListener? = null
    private lateinit var rowDescriptor: IosDescriptor
    private var mWidgetRowValueLabel: TextView

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


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
        listener?.onRowClick(this)
    }

    override fun notifyDataChanged() {
        if (rowDescriptor.icon == 0) {
            mWidgetRowIconImg.visibility = GONE
        } else {
            mWidgetRowIconImg.setBackgroundResource(rowDescriptor.icon)
        }
        mWidgetRowLabel.text = rowDescriptor.label
        mWidgetRowValueLabel.text = rowDescriptor.getValue()
        if (rowDescriptor.id != 0) {
            setBackgroundResource(R.drawable.widgets_general_row_select)
            mWidgetRowActionImg.setBackgroundResource(R.drawable.action_row)
            mWidgetRowActionImg.visibility = VISIBLE
            setOnClickListener(this)
        } else {
            mWidgetRowActionImg.visibility = GONE
        }
    }

    override fun getRowId(): Int {
        return rowDescriptor.id
    }

    override fun initData(rowDescriptor: IRowDescriptor, listener: OnRowClickListener?) {
        this.rowDescriptor = rowDescriptor as IosDescriptor
        this.listener = listener
    }
}