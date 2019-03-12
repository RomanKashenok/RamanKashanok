package com.kashanok.classes.homework.hw5

import android.app.Service
import android.content.* // ktlint-disable no-wildcard-imports
import android.os.Bundle
import android.os.IBinder
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.kashanok.classes.R
import kotlinx.android.synthetic.main.activity_hw5.*

class Hw5Activity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, Hw5Activity::class.java)
        }
    }

    private lateinit var serviceConnection: ServiceConnection
    private lateinit var wifiCheckerService: WifiCheckerService

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(contxt: Context?, intent: Intent?) {
            when (intent?.action) {
                WifiCheckerService.WIFI_STATE_ACTION -> setActualWifiState(
                    intent.getBooleanExtra(WifiCheckerService.EXTRA_KEY, false)
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.kashanok.classes.R.layout.activity_hw5)

        serviceConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, binder: IBinder) {
                wifiCheckerService = (binder as WifiCheckerBinder).getService() as WifiCheckerService
                Log.e("Hw5Activity", "WifiCheckerService connected ***")
                wifiCheckerService.getWifiState(this@Hw5Activity)
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                Log.e("Hw5Activity", "WifiCheckerService disconnected")
            }
        }
    }

    override fun onStart() {
        super.onStart()
        bindService(Intent(this, WifiCheckerService::class.java), serviceConnection, Service.BIND_AUTO_CREATE)
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(receiver, IntentFilter(WifiCheckerService.WIFI_STATE_ACTION))
    }

    override fun onStop() {
        super.onStop()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver)
        unbindService(serviceConnection)
    }

    private fun setActualWifiState(booleanExtra: Boolean) {
        stateText.text = booleanExtra.toString()
        Toast.makeText(this, getString(R.string.hw5_wifi_state) + booleanExtra.toString(), Toast.LENGTH_LONG).show()
    }
}
