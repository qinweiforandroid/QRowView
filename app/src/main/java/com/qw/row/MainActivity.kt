package com.qw.row

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.qw.row.library.*

class MainActivity : AppCompatActivity(), OnRowClickListener {

    private lateinit var mContainerView: ContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mContainerView = findViewById(R.id.mContainerView)
        val groups = ArrayList<GroupDescriptor>()
        groups.add(
            GroupDescriptor.Builder("")
                .setDividerColor(Color.parseColor("#e5e5e5"))
                .setDividerHeight(dip2px(1f))
                .setDividerLeftMargin(dip2px(8f))
                .addRow(
                    GeneralDescriptor.Builder(1)
                        .setIcon(R.mipmap.ic_launcher)
                        .setLabel("朋友圈")
                        .build()
                )
                .addRow(
                    GeneralDescriptor.Builder(2)
                        .setIcon(R.mipmap.ic_launcher)
                        .setLabel("朋友圈2")
                        .build()
                )
                .addRow(
                    GeneralDescriptor.Builder(3)
                        .setIcon(R.mipmap.ic_launcher)
                        .setLabel("朋友圈3")
                        .build()
                )
                .build()
        )
        mContainerView.initData(
            ContainerDescriptor.Builder(groups)
                .setTopMargin(dip2px(16f))
                .build(), this
        )
        mContainerView.notifyDataChanged()
    }

    override fun onRowClick(rowView: AbsRow) {

    }
}