package com.kashanok.classes.classwork.cw5

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService: Service() {

    override fun onBind(intent: Intent?): IBinder? {
        println("*********** MyService onBind() ***********")
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        println("*********** MyService onStartCommand() ***********")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()
        println("*********** MyService onCreate() ***********")
    }

    override fun onDestroy() {
        println("*********** MyService onDestroy() ***********")
        super.onDestroy()
    }
}