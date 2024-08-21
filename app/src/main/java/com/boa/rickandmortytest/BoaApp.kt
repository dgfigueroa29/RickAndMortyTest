package com.boa.rickandmortytest

import android.app.Application
import timber.log.Timber

class BoaApp: Application(){
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}