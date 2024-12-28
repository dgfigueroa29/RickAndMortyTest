package com.boa.rickandmortytest.presentation.sensor

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class ConnectivityReceiver(private val onNetworkChange: (Boolean) -> Unit) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        val isConnected =
            capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false

        onNetworkChange(isConnected)
    }
}
