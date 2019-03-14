package com.kashanok.classes.common

import android.content.Context
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request

object DataPicker {

    fun getDataFromUrl(url: String): String {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()
        val response = client.newCall(request).execute()
        return response.body().string()
    }

    fun getDataFromAssets(context: Context, fileName: String): String {
        val inStr = context.assets.open(fileName)
        val buffer = ByteArray(inStr.available())
        inStr.read(buffer)
        inStr.close()
        return String(buffer)
    }
}