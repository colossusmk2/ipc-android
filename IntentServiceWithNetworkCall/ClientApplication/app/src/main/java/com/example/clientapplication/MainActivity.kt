package com.example.clientapplication

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    val handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            var bundle = msg?.obj as Bundle
            Log.d("testAIDL", "handleMessage " + bundle.getString("transactions"))
        }
    }

    val messenger = Messenger(handler)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startServiceAndGetResult(view: View) {
        Log.d("testAIDL", "starting service to fetch results")
        val sdkIntent = Intent()
        sdkIntent.putExtra("ipcMessanger", messenger)
        sdkIntent.component = ComponentName(
            "com.example.serverapplication",
            "com.example.serverapplication.RemoteService"
        )
        startService(sdkIntent)
    }
}