package com.kashanok.classes.homework.common

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import com.kashanok.classes.common.BaseAdapterItem
import com.kashanok.classes.common.BaseItemClickListener
import com.kashanok.classes.homework.common.workitem.HomeWorkNumber
import com.kashanok.classes.homework.common.workitem.WorkNumber
import com.kashanok.classes.homework.hw1.ReplacingActivity
import com.kashanok.classes.homework.hw2.FlagsActivity
import com.kashanok.classes.homework.hw3.LoadPictureActivity
import com.kashanok.classes.homework.hw4.Hw4Activity
import com.kashanok.classes.homework.hw5.Hw5Activity
import com.kashanok.classes.homework.hw6.Hw6MainActivity


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
                context.startActivity(LoadPictureActivity.getIntent(context))
            }
            HomeWorkNumber.FOUR -> {
                context.startActivity(Hw4Activity.getIntent(context))
            }
            HomeWorkNumber.FIVE -> {
                context.startActivity(Hw5Activity.getIntent(context))
            }
            HomeWorkNumber.SIX -> {
                if(isNetworkConnected()){
                    context.startActivity(Hw6MainActivity.getIntent(context))
                } else {
                    Toast.makeText(context, "CHECK YOUR INTERNET CONNECTION", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun isNetworkConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork?.isConnected ?: false
    }
}