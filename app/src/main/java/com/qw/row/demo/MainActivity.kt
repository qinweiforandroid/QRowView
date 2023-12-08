package com.qw.row.demo

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.qw.row.ContainerDescriptor
import com.qw.row.ContainerView
import com.qw.row.core.AbsGroupDescriptor
import com.qw.row.core.AbsRowView
import com.qw.row.core.OnRowChangedListener
import com.qw.row.group.GroupDescriptor
import com.qw.row.item.AndroidDescriptor

class MainActivity : AppCompatActivity(), OnRowChangedListener {

    private lateinit var mContainerView: ContainerView

    private val ROW_BANK = 2
    private val ROW_COLLECT = 3
    private val ROW_FRIEND = 4
    private val ROW_EMOJI = 5
    private val ROW_SETTING = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mContainerView = findViewById(R.id.mContainerView)
        val groups = ArrayList<AbsGroupDescriptor>()
        groups.add(genGroup().apply {
            addRow(AndroidDescriptor(ROW_BANK, R.drawable.more_my_bank_card, "服务"))
        })
        groups.add(genGroup().apply {
            addRow(AndroidDescriptor(ROW_COLLECT, R.drawable.more_my_favorite, "收藏"))
            addRow(AndroidDescriptor(ROW_FRIEND, R.drawable.more_my_album, "朋友圈"))
            addRow(AndroidDescriptor(ROW_EMOJI, R.drawable.more_emoji_store, "表情"))
        })

        groups.add(genGroup().apply {
            addRow(AndroidDescriptor(ROW_SETTING, R.drawable.more_setting, "设置"))
        })
        mContainerView.initData(ContainerDescriptor(groups, dip2px(10f)), this)
        mContainerView.notifyDataChanged()
    }

    private fun genGroup(): GroupDescriptor {
        return GroupDescriptor().setBackgroundColor(Color.WHITE)
            .setDividerColor(Color.parseColor("#e5e5e5"))
            .setDividerHeight(dip2px(1f))
            .setDividerLeftMargin(dip2px(8f))
    }

    override fun onRowChanged(row: AbsRowView<*>) {
        Toast.makeText(this, "id:${row.id}", Toast.LENGTH_SHORT).show()
    }
}