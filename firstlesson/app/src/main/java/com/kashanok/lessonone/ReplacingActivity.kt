package com.kashanok.lessonone

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.kashanok.lessonone.listeners.AnyViewClickListener
import kotlinx.android.synthetic.main.title_activity_replacing.*

class ReplacingActivity: Activity(), View.OnClickListener {

    private lateinit var clickListener: View.OnClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.title_activity_replacing)
        clickListener = AnyViewClickListener(leftText, rightText)

//        //First approach
//        setAllItemsClickListener(leftText, rightText, changeButton)

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