package com.kashanok.classes.homework.common

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.kashanok.classes.R
import com.kashanok.classes.common.BaseAdapterItem
import com.kashanok.classes.common.BaseItemClickListener
import com.kashanok.classes.common.ItemOffsetDecoration
import com.kashanok.classes.homework.common.workitem.HomeWorkNumber
import com.kashanok.classes.homework.common.workitem.HomeworkListItem
import com.kashanok.classes.homework.common.workitem.WorkNumber
import com.kashanok.classes.homework.hw1.ReplacingActivity
import com.kashanok.classes.homework.hw2.FlagsActivity
import kotlinx.android.synthetic.main.activity_home_works.*

class HomeWorkStartScreenActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, HomeWorkStartScreenActivity::class.java)
        }
    }

    private var homeworkItemsAdapter: HomeworkListItemAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_works)

        homeworkItemsAdapter = HomeworkListItemAdapter(getItemClickListener(this), this)

        homeworkList.layoutManager = LinearLayoutManager(this)
        homeworkList.adapter = homeworkItemsAdapter

        homeworkItemsAdapter?.setNewItems(loadHomeworks())

        homeworkList.addItemDecoration(getItemDecoration())
    }

    private fun loadHomeworks(): List<BaseAdapterItem<WorkNumber>> {
        val itemsList = ArrayList<BaseAdapterItem<WorkNumber>>()
        HomeWorkNumber.values().forEach {
            itemsList.add(HomeworkListItem(WorkNumber(it.title, it.desc, it.image)))
        }
        return itemsList
    }

    private fun getItemDecoration(): ItemOffsetDecoration {
        return ItemOffsetDecoration(
            this,
            R.dimen.dimen_0dp,
            R.dimen.dimen_9dp,
            R.dimen.dimen_9dp,
            R.dimen.dimen_9dp,
            R.dimen.dimen_0dp,
            R.dimen.dimen_27dp
        )
    }

    private fun getItemClickListener(context: Context): BaseItemClickListener<BaseAdapterItem<WorkNumber>> {
        return object : BaseItemClickListener<BaseAdapterItem<WorkNumber>> {
            override fun onItemClicked(model: BaseAdapterItem<WorkNumber>, position: Int, clickType: Int) {

                val work: HomeWorkNumber = HomeWorkNumber.fromOrdinal(position)
                when (work) {
                    HomeWorkNumber.FIRST -> {
                        startActivity(ReplacingActivity.getIntent(context))
                    }
                    HomeWorkNumber.SECOND -> {
                        startActivity(FlagsActivity.getIntent(context))
                    }
                    HomeWorkNumber.THIRD -> {
                        val toast = Toast.makeText(context, getString(R.string.under_construction), Toast.LENGTH_LONG)
                        toast.show()
                    }
                }
            }
        }
    }
}
