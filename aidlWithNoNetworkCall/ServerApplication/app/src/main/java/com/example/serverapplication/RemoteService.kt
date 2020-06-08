package com.example.serverapplication

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.test.testpay.TestpayServiceBinder

class RemoteService : Service() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(intent: Intent): IBinder {
        // Return the interface
        return binder
    }


    private val binder = object : TestpayServiceBinder.Stub() {
        override fun sayHello(): String {
            return "hello back"
        }
    }
}
