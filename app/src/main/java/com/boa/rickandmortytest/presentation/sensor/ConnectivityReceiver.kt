package com.boa.rickandmortytest.presentation.sensor

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import timber.log.Timber

class ConnectivityReceiver(private val onNetworkChange: (Boolean) -> Unit) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        Timber.e("*** CONNECTIVITY: $${connectivityManager.allNetworks}")
        val activeNetwork = connectivityManager.activeNetworkInfo?.isConnected ?: false
        onNetworkChange(activeNetwork)
    }
}