package com.example.apimvvmunitdemo.utils.logger

import android.util.Log
import com.example.apimvvmunitdemo.utils.logger.Logger

class AppLogger : Logger {
    override fun d(tag: String, msg: String) {
        Log.d(tag, msg)
    }
}