package com.boa.rickandmortytest.presentation.component

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.boa.rickandmortytest.presentation.sensor.ConnectivityReceiver

@Composable
fun ConnectivityStatus(onConnectionChanged: (Boolean) -> Unit) {
    val context = LocalContext.current
    val connectivityReceiver = remember {
        ConnectivityReceiver(onConnectionChanged)
    }

    DisposableEffect(Unit) {
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(connectivityReceiver, intentFilter)

        onDispose {
            context.unregisterReceiver(connectivityReceiver)
        }
    }
}