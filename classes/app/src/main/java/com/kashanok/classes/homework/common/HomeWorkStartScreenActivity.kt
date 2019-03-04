package com.kashanok.classes.homework.common

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.kashanok.classes.R
import com.kashanok.classes.classwork.cw2.Cw2Activity
import com.kashanok.classes.classwork.cw3.Cw3Activity
import com.kashanok.classes.classwork.cw4.Cw4Activity
import com.kashanok.classes.common.BaseAdapterItem
import com.kashanok.classes.common.ItemOffsetDecoration
import com.kashanok.classes.homework.common.workitem.HomeWorkNumber
import com.kashanok.classes.homework.common.workitem.HomeworkListItem
import com.kashanok.classes.homework.common.workitem.WorkNumber
import kotlinx.android.synthetic.main.activity_home_works.*

class HomeWorkStartScreenActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, HomeWorkStartScreenActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_works)

        val homeworkItemsAdapter = HomeworkListItemAdapter(HomeworkClickListener(this), this)

        homeworkList.layoutManager = LinearLayoutManager(this)
        homeworkList.adapter = homeworkItemsAdapter

        homeworkItemsAdapter.setNewItems(loadHomeworks())

        homeworkList.addItemDecoration(getItemDecoration())

        bindClassworkButtons()
    }

    private fun loadHomeworks(): List<BaseAdapterItem<WorkNumber>> {
        val itemsList = ArrayList<BaseAdapterItem<WorkNumber>>()
        HomeWorkNumber.values().forEach {
            itemsList.add(HomeworkListItem(WorkNumber(it.title, it.desc, it.image)))
        }
        return itemsList
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
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

    private fun bindClassworkButtons() {
        cw2Button.setOnClickListener { startActivity(Cw2Activity.getIntent(this)) }
        cw3Button.setOnClickListener { startActivity(Cw3Activity.getIntent(this)) }
        cw4Button.setOnClickListener { startActivity(Cw4Activity.getIntent(this)) }
    }
}
