package com.qw.row.demo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.qw.row.ContainerDescriptor
import com.qw.row.ContainerView
import com.qw.row.core.AbsGroupDescriptor
import com.qw.row.core.AbsRowView
import com.qw.row.core.OnRowChangedListener
import com.qw.row.demo.ext.CardGroupDescriptor
import com.qw.row.group.GroupDescriptor
import com.qw.row.item.AndroidDescriptor
import com.qw.row.item.SwitchDescriptor
import com.qw.row.item.SwitchRowView

class MainActivity : AppCompatActivity(), OnRowChangedListener {

    private lateinit var mContainerView: ContainerView

    companion object {
        private const val ROW_BANK = 2
        private const val ROW_COLLECT = 3
        private const val ROW_FRIEND = 4
        private const val ROW_EMOJI = 5
        private const val ROW_SETTING = 6
        private const val ROW_NIGHT = 7
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mContainerView = findViewById(R.id.mContainerView)
        val groups = ArrayList<AbsGroupDescriptor>()
        groups.add(genGroup().apply {
            addRow(AndroidDescriptor(ROW_BANK, R.drawable.more_my_bank_card, "银行卡"))
        })
        groups.add(genGroup().apply {
            addRow(AndroidDescriptor(ROW_COLLECT, R.drawable.more_my_favorite, "收藏"))
            addRow(AndroidDescriptor(ROW_FRIEND, R.drawable.more_my_album, "朋友圈"))
            addRow(AndroidDescriptor(ROW_EMOJI, R.drawable.more_emoji_store, "表情"))
        })
        groups.add(genGroup().apply {
            addRow(AndroidDescriptor(ROW_SETTING, R.drawable.more_setting, "设置"))
        })

        groups.add(genGroupCard().apply {
            addRow(AndroidDescriptor(ROW_BANK, R.drawable.more_my_bank_card, "银行卡"))
        })
        groups.add(genGroupCard().apply {
            addRow(AndroidDescriptor(ROW_COLLECT, R.drawable.more_my_favorite, "收藏"))
            addRow(AndroidDescriptor(ROW_FRIEND, R.drawable.more_my_album, "朋友圈"))
            addRow(AndroidDescriptor(ROW_EMOJI, R.drawable.more_emoji_store, "表情"))
            val isChecked = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
            addRow(SwitchDescriptor(ROW_NIGHT, R.drawable.more_setting, "暗黑模式", isChecked))
        })
        mContainerView.initData(ContainerDescriptor(groups, dip2px(10f)), this)
        mContainerView.notifyDataChanged()
    }

    private fun genGroupCard(): CardGroupDescriptor {
        return CardGroupDescriptor()
            .setDividerColor(
                ContextCompat.getColor(this, com.qw.row.R.color.widgets_general_row_line)
            )
            .setDividerHeight(dip2px(0.5f))
            .setDividerLeftMargin(dip2px(44f))
    }

    private fun genGroup(title: String = ""): GroupDescriptor {
        return GroupDescriptor(title)
            .setBackgroundColor(
                ContextCompat.getColor(this, com.qw.row.R.color.widgets_general_row_normal)
            ).setDividerColor(
                ContextCompat.getColor(this, com.qw.row.R.color.widgets_general_row_line)
            )
            .setDividerHeight(dip2px(0.5f))
            .setDividerLeftMargin(dip2px(44f))
    }

    override fun onRowChanged(row: AbsRowView<*>) {
        Toast.makeText(this, "id:${row.id}", Toast.LENGTH_SHORT).show()
        when (row.id) {
            ROW_NIGHT -> {
                val isChecked = (row as SwitchRowView).isChecked
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }
    }
}