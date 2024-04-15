package com.example.apimvvmunitdemo.data.api

import com.example.apimvvmunitdemo.di.NetworkApiKey
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(@NetworkApiKey private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder().header("X-Api-Key", apiKey)
//        val token = ""
//        requestBuilder.addHeader("Authorization", "Bearer $token")
//        requestBuilder.addHeader("Accept","application/json")
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}