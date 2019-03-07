package com.kashanok.classes.classwork.cw5

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import com.kashanok.classes.R
import kotlinx.android.synthetic.main.activity_cw5.*

class Cw5Activity : Activity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, Cw5Activity::class.java)
        }
    }

    private lateinit var receiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cw5)

        receiver = MyReceiver()

        /** Sending Broadcast intent*/
//        buttonView.setOnClickListener {
//            LocalBroadcastManager.getInstance(this).sendBroadcast(Intent("CUSTOM_ACTION"))
//        }

        /** Starting custom Service*/
        startService(Intent(this, MyService::class.java).putExtra("KEY1", "Value to used in service"))
    }

    override fun onResume() {
        super.onResume()
        /** Registering custom BroadcastReceiver*/
//        registerReceiver(receiver, IntentFilter("android.intent.action.AIRPLANE_MODE"))

        /** Registering custom BroadcastReceiver LOCALLY - only inside application!!!*/
//        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, IntentFilter("CUSTOM_ACTION"))
    }

    override fun onPause() {
        super.onPause()
        /** Unregistering custom BroadcastReceiver*/
//        unregisterReceiver(receiver)

        /** Unregistering custom BroadcastReceiver from LOCAL APPLICATION SCOPE!!!*/
//        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver)
    }

    override fun onDestroy() {
        super.onDestroy()

        /** Stopping custom Service*/
        stopService(Intent(this, MyService::class.java))
    }
}