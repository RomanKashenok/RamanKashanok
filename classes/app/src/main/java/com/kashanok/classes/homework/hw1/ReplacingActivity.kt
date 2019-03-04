package com.kashanok.classes.homework.hw1

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.kashanok.classes.R
import com.kashanok.classes.homework.hw1.listeners.AnyViewClickListener
import kotlinx.android.synthetic.main.activity_hw1.*

class ReplacingActivity: Activity(), View.OnClickListener {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, ReplacingActivity::class.java)
        }
    }

    private lateinit var clickListener: View.OnClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hw1)
        clickListener = AnyViewClickListener(leftText, rightText)

        //First approach
        setAllItemsClickListener(leftText, rightText, changeButton)

//        //Second approach
//        leftText.setOnClickListener { changeColorsAndText() }
//        rightText.setOnClickListener { changeColorsAndText() }
//        changeButton.setOnClickListener { changeColorsAndText() }

        //Third approach
//        leftText.setOnClickListener(this)
//        rightText.setOnClickListener(this)
//        changeButton.setOnClickListener(this)

    }

    //First approach
    private fun setAllItemsClickListener(vararg view: View) {
        if (view.size == 3) {
            view.forEach { it -> it.setOnClickListener(clickListener)
            }
        }
    }

    //Second approach
    private fun changeColorsAndText() {
        changeAction()
    }

    //Third approach
    override fun onClick(view: View?) {
        changeAction()
    }

    private fun changeAction() {
        val text = leftText.text
        val background = leftText.background
        leftText.background = rightText.background
        leftText.text = rightText.text
        rightText.background = background
        rightText.text = text
    }


}