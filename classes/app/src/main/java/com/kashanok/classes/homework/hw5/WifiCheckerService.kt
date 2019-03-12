package com.kashanok.classes.homework.hw5

import android.app.Service
import android.content.* // ktlint-disable no-wildcard-imports
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.os.Binder
import android.os.IBinder
import android.support.v4.content.LocalBroadcastManager

class WifiCheckerService : Service() {

    companion object {
        const val WIFI_STATE_ACTION = "com.kashanok.classes.homework.hw5.WifiCheckerService.WIFI_STATE_ACTION"
        const val EXTRA_KEY = "EXTRA_KEY"
    }

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(contxt: Context?, intent: Intent?) {
            when (intent?.action) {
                WifiManager.NETWORK_STATE_CHANGED_ACTION -> getWifiState(intent)
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        val filter = IntentFilter()
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION)
        registerReceiver(receiver, filter)
        return WifiCheckerBinder()
    }

    override fun unbindService(conn: ServiceConnection) {
        super.unbindService(conn)
        unregisterReceiver(receiver)
    }

    fun getWifiState(context: Context) {
        val connManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        networkInfo.isConnected
        LocalBroadcastManager.getInstance(this)
            .sendBroadcast(Intent(WIFI_STATE_ACTION).putExtra(EXTRA_KEY, networkInfo.isConnected))
    }

    fun getWifiState(intent: Intent) {
        val networkInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO) as NetworkInfo
        networkInfo.isConnected
        LocalBroadcastManager.getInstance(this)
            .sendBroadcast(Intent(WIFI_STATE_ACTION).putExtra(EXTRA_KEY, networkInfo.isConnected))
    }
}

class WifiCheckerBinder : Binder() {
    fun getService(): Service {
        return WifiCheckerService()
    }
}