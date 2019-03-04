package com.kashanok.classes.classwork.cw2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kashanok.classes.R
import kotlinx.android.synthetic.main.activity_cw2.*

class Cw2Activity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, Cw2Activity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cw2)

        button.setOnClickListener {
            startActivity(
                ToGoToActivity.getIntent(
                    this,
                    getString(R.string.button_back_button)
                )
            )
        }
    }
}
