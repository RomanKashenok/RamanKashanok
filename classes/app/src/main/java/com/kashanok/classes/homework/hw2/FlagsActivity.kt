package com.kashanok.classes.homework.hw2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kashanok.classes.R

class FlagsActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, FlagsActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flags)
    }
}
