package com.d121211104.bundle

import android.app.Application
import com.d121211104.bundle.data.AppContainer
import com.d121211104.bundle.data.DefaultAppContainer

class MyApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}