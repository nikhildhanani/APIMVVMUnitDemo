package com.example.apimvvmunitdemo.utils

import android.content.Context
import com.example.apimvvmunitdemo.R

interface ResourceProvider {
    fun getStringNoInternetAvailable(): String
}

class DefaultResourceProvider(
    private val context: Context
) : ResourceProvider {
    override fun getStringNoInternetAvailable(): String {
        return context.getString(R.string.no_internet_available)
    }

}