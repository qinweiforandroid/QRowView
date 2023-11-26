package com.qw.row

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.LinearLayout
import com.qw.row.core.AbsGroupView
import com.qw.row.core.IGroupDescriptor
import com.qw.row.core.OnRowClickListener

class ContainerView : LinearLayout, OnTouchListener {
    private var listener: OnRowClickListener? = null
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

    fun initData(descriptor: ContainerDescriptor, listener: OnRowClickListener?) {
        this.descriptor = descriptor
        this.listener = listener
    }

    fun notifyDataChanged() {
        removeAllViews()
        if (descriptor.groups.size > 0) {
            var groupView: AbsGroupView
            val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            params.topMargin = descriptor.space
            for (i in descriptor.groups.indices) {
                groupView = createGroup(descriptor.groups[i].getViewClass())
                groupView.initData(descriptor.groups[i], listener)
                groupView.notifyDataChanged()
                addView(groupView, params)
            }
        } else {
            visibility = GONE
        }
    }

    private fun createGroup(clazz: Class<out AbsGroupView>): AbsGroupView {
        val constructor = clazz.getDeclaredConstructor(Context::class.java)
        constructor.isAccessible = true
        return constructor.newInstance(context) as AbsGroupView
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

class ContainerDescriptor(var groups: ArrayList<IGroupDescriptor>, var space: Int)