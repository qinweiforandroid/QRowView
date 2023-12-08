package com.qw.row

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.LinearLayout
import com.qw.row.core.AbsGroupDescriptor
import com.qw.row.core.AbsGroupView
import com.qw.row.core.AbsRowDescriptor
import com.qw.row.core.AbsRowView
import com.qw.row.core.OnRowChangedListener

class ContainerView : LinearLayout, OnTouchListener {
    private var listener: OnRowChangedListener? = null
    private lateinit var descriptor: ContainerDescriptor

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context) : super(context)

    init {
        orientation = VERTICAL
        setOnTouchListener(this)
    }

    fun initData(descriptor: ContainerDescriptor, listener: OnRowChangedListener?) {
        this.descriptor = descriptor
        this.listener = listener
    }

    fun notifyDataChanged(rowId: Int, descriptor: AbsRowDescriptor) {
        val row = findViewById<View>(rowId) as? AbsRowView<AbsRowDescriptor>
        if (row != null) {
            row.notifyDataChanged(descriptor)
        } else {
            require(!BuildConfig.DEBUG) { "can't find the row by id" }
        }
    }

    fun notifyDataChanged() {
        removeAllViews()
        if (descriptor.groups.size > 0) {
            var groupView: AbsGroupView<AbsGroupDescriptor>
            val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            params.topMargin = descriptor.space
            for (i in descriptor.groups.indices) {
                groupView = createGroup(descriptor.groups[i].getViewClass())
                groupView.initData(descriptor.groups[i],listener)
                groupView.notifyDataChanged()
                addView(groupView, params)
            }
        } else {
            visibility = GONE
        }
    }

    private fun createGroup(clazz: Class<*>): AbsGroupView<AbsGroupDescriptor> {
        val constructor = clazz.getDeclaredConstructor(Context::class.java)
        constructor.isAccessible = true
        return constructor.newInstance(context) as AbsGroupView<AbsGroupDescriptor>
    }

    /**
     * @param v
     * @param event
     * @return
     * @see OnTouchListener.onTouch
     */
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        return false
    }

    /**
     * @param event
     * @return
     * @see View.onTouchEvent
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return super.onTouchEvent(event)
    }

    /**
     * @param ev
     * @return
     * @see android.view.ViewGroup.onInterceptTouchEvent
     */
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if (ev.pointerCount > 1) {
            return true
        }
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {}
            else -> {}
        }
        return false
    }
}

class ContainerDescriptor(var groups: ArrayList<AbsGroupDescriptor>, var space: Int)