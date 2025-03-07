package com.example.aroundegypt

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AroundEgyptApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("AroundEgyptApp", "onCreate")
    }
}