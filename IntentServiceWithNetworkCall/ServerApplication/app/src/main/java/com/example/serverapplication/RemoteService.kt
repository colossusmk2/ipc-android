package com.example.serverapplication

import android.app.IntentService
import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.os.Messenger
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class RemoteService : IntentService("Testing") {
    var apiResponse: String = "default"

    override fun onHandleIntent(intent: Intent?) {
        val resultReceiver = intent?.getParcelableExtra<Messenger>("ipcMessanger")
        val url: URL
        var urlConnection: HttpURLConnection? = null
        try {
            //create this url with mocky with simple string in response
            url = URL("https://run.mocky.io/v3/c79bb75e-b710-49a5-84d0-f4429a64f4a3")
            urlConnection = url.openConnection() as HttpURLConnection
            val inputStream: InputStream = urlConnection.inputStream
            val isw = InputStreamReader(inputStream)
            var data: Int = isw.read()
            apiResponse = ""
            while (data != -1) {
                val current = data.toChar()
                apiResponse += current
                data = isw.read()

            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            urlConnection?.disconnect()
        }
        val bundle = Bundle()
        bundle.putString("transactions", apiResponse)
        val msg = Message.obtain(null, 123)
        msg.obj = bundle
        resultReceiver?.send(msg)
    }
}
