package com.kashanok.lessonone

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.kashanok.lessonone.listeners.AnyViewClickListener

class ReplacingActivity : Activity(), View.OnClickListener {

    companion object {
        private lateinit var leftTextView: TextView
        private lateinit var rightTextView: TextView
        private lateinit var changeButton: Button
    }

    private lateinit var clickListener: View.OnClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.title_activity_replacing)
        leftTextView = findViewById(R.id.leftText)
        rightTextView = findViewById(R.id.rightText)
        changeButton = findViewById(R.id.changeButton)
        clickListener = AnyViewClickListener(leftTextView, rightTextView)

//        //First approach
//        setAllItemsClickListener(leftTextView, rightTextView, changeButton)

//        //Second approach
//        leftTextView.setOnClickListener { changeColorsAndText() }
//        rightTextView.setOnClickListener { changeColorsAndText() }
//        changeButton.setOnClickListener { changeColorsAndText() }

        //Third approach
//        leftTextView.setOnClickListener(ReplacingActivity())
//        rightTextView.setOnClickListener(ReplacingActivity())
//        changeButton.setOnClickListener(ReplacingActivity())

    }

    //First approach
    private fun setAllItemsClickListener(vararg view: View) {
        if (view.size == 3) {
            view.forEach { view -> view.setOnClickListener(clickListener)
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
        val text = leftTextView.text
        val background = leftTextView.background
        leftTextView.background = rightTextView.background
        leftTextView.text = rightTextView.text
        rightTextView.background = background
        rightTextView.text = text
    }


}