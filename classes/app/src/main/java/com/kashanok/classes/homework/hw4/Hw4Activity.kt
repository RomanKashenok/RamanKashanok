package com.kashanok.classes.homework.hw4

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kashanok.classes.R
import kotlinx.android.synthetic.main.activity_hw4.*

class Hw4Activity : AppCompatActivity() {

    companion object {
        const val MIN_DELAY = 500L
        const val MAX_DELAY = 5000L

        fun getIntent(context: Context): Intent {
            return Intent(context, Hw4Activity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hw4)

        pager.adapter = Hw4PagerAdapter(this)
    }
}
