package com.example.clientapplication

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.test.testpay.TestpayServiceBinder

class MainActivity : AppCompatActivity() {

    var testpayServiceBinder: TestpayServiceBinder? = null
    val jiopayServiceConn = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.d("testAIDL", "onServiceConnected")
            testpayServiceBinder = TestpayServiceBinder.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d("testAIDL", "onServiceDisconnected")
            testpayServiceBinder = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun bindWithJiopay(v: View) {
        Log.d("testAIDL", "binding")
        val sdkIntent = Intent()
        sdkIntent.component = ComponentName(
            "com.example.serverapplication",
            "com.example.serverapplication.RemoteService"
        )
        bindService(sdkIntent, jiopayServiceConn, Context.BIND_AUTO_CREATE)
    }

    fun sayHello(v: View) {
        Log.d("testAIDL", "sayHello")
        var helloString: String? = "Hello"
        if(testpayServiceBinder != null) {
            helloString = testpayServiceBinder?.sayHello()
        }
        Log.d("testAIDL", "$helloString")
    }

    fun unbindJiopayService(v: View) {
        Log.d("testAIDL", "unbinding")
        unbindService(jiopayServiceConn)
    }

}
