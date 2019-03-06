package com.kashanok.classes.classwork.cw5

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    companion object {
        const val CUSTOM_INTENT = "com.kashanok.classes.classwork.cw5.MyReceiver.CUSTOM_EVENT"
    }

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "Airplace MOdeChanged", Toast.LENGTH_LONG).show()
        Log.e("AAAAAAA", "onReceive DETECTED!!!")
    }
}