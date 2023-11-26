package com.qw.row.demo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.qw.row.ContainerDescriptor
import com.qw.row.ContainerView
import com.qw.row.core.AbsRowView
import com.qw.row.core.IGroupDescriptor
import com.qw.row.core.OnRowClickListener
import com.qw.row.group.GroupDescriptor
import com.qw.row.item.AndroidDescriptor
import com.qw.row.item.IosDescriptor

class MainActivity : AppCompatActivity(), OnRowClickListener {

    private lateinit var mContainerView: ContainerView

    companion object {
        val id_friend = 1
        val id_scan = 2
        val id_shark = 3
        val id_look = 4
        val id_search = 5
        val id_nearby = 6
        val id_shop = 7
        val id_game = 8
        val id_mini = 9
        val id_open_close = 10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mContainerView = findViewById(R.id.mContainerView)
        val groups = ArrayList<IGroupDescriptor>()
        groups.add(
            genGroup().addRow(
                IosDescriptor(id_mini, R.mipmap.ic_launcher, "手机号")
                    .setValue("17091314320")
            )
        )

        groups.add(genGroup().apply {
            addRow(AndroidDescriptor(id_scan, R.mipmap.ic_launcher, "扫一扫"))
            addRow(AndroidDescriptor(id_shark, R.mipmap.ic_launcher, "摇一摇"))
        })
        groups.add(genGroup().apply {
            addRow(AndroidDescriptor(id_search, R.mipmap.ic_launcher, "搜一搜"))
        })
        groups.add(genGroup().apply {
            addRow(AndroidDescriptor(id_nearby, R.mipmap.ic_launcher, "附近的人"))
        })
        groups.add(genGroup().apply {
            addRow(AndroidDescriptor(id_shop, R.mipmap.ic_launcher, "购物"))
            addRow(AndroidDescriptor(id_game, R.mipmap.ic_launcher, "游戏"))
        })
        mContainerView.initData(ContainerDescriptor(groups, dip2px(10f)), this)
        mContainerView.notifyDataChanged()
    }

    private fun genGroup(): GroupDescriptor {
        return GroupDescriptor.Builder()
            .setBackgroundColor(Color.WHITE)
            .setDividerColor(Color.parseColor("#e5e5e5"))
            .setDividerHeight(dip2px(1f))
            .setDividerLeftMargin(dip2px(8f))
            .builder()
    }

    override fun onRowClick(rowView: AbsRowView) {

    }
}