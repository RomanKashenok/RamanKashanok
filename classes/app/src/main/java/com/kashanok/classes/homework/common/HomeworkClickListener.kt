package com.kashanok.classes.homework.common

import android.content.Context
import android.widget.Toast
import com.kashanok.classes.R
import com.kashanok.classes.common.BaseAdapterItem
import com.kashanok.classes.common.BaseItemClickListener
import com.kashanok.classes.homework.common.workitem.HomeWorkNumber
import com.kashanok.classes.homework.common.workitem.WorkNumber
import com.kashanok.classes.homework.hw1.ReplacingActivity
import com.kashanok.classes.homework.hw2.FlagsActivity

class HomeworkClickListener(val context: Context) : BaseItemClickListener<BaseAdapterItem<WorkNumber>> {

    override fun onItemClicked(model: BaseAdapterItem<WorkNumber>, position: Int, clickType: Int) {
        val work: HomeWorkNumber = HomeWorkNumber.fromOrdinal(position)
        when (work) {
            HomeWorkNumber.FIRST -> {
                context.startActivity(ReplacingActivity.getIntent(context))
            }
            HomeWorkNumber.SECOND -> {
                context.startActivity(FlagsActivity.getIntent(context))
            }
            HomeWorkNumber.THIRD -> {
                val toast = Toast.makeText(
                    context,
                    context.getString(R.string.under_construction),
                    Toast.LENGTH_LONG
                )
                toast.show()
            }
        }
    }
}