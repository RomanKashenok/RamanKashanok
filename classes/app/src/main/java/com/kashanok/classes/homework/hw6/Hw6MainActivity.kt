package com.kashanok.classes.homework.hw6

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.kashanok.classes.common.ItemOffsetDecoration
import com.kashanok.classes.homework.hw6.recycler.Hw6RvAdapter
import kotlinx.android.synthetic.main.activity_hw6.*
import com.kashanok.classes.R.layout.*

class Hw6MainActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, Hw6MainActivity::class.java)
        }
    }

    private var rvAdapter: Hw6RvAdapter? = null
    private var presenter: StudentsDataPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_hw6)

        rvStudents.layoutManager = LinearLayoutManager(this)

        rvAdapter = Hw6RvAdapter(this)
        rvStudents.adapter = rvAdapter
        rvStudents.setHasFixedSize(true)
        rvStudents.addItemDecoration(getItemDecoration())

        presenter = StudentsDataPresenter(rvAdapter)
        presenter?.execute()

        addButton.setOnClickListener {
            startActivity(Intent(this, Hw6EditActivity::class.java))
        }
    }

    private fun getItemDecoration(): ItemOffsetDecoration {
        return ItemOffsetDecoration(
            this,
            com.kashanok.classes.R.dimen.dimen_27dp,
            com.kashanok.classes.R.dimen.dimen_9dp,
            com.kashanok.classes.R.dimen.dimen_9dp,
            com.kashanok.classes.R.dimen.dimen_9dp,
            com.kashanok.classes.R.dimen.dimen_0dp,
            com.kashanok.classes.R.dimen.dimen_27dp
        )
    }
}
