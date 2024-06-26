package com.example.apimvvmunitdemo.di.module

import android.content.Context
import com.example.apimvvmunitdemo.utils.logger.AppLogger
import com.example.apimvvmunitdemo.utils.logger.Logger
import com.example.apimvvmunitdemo.data.api.NetworkService

import com.example.apimvvmunitdemo.di.BaseUrl
import com.example.apimvvmunitdemo.di.NetworkApiKey
import com.example.apimvvmunitdemo.utils.Constants.API_KEY
import com.example.apimvvmunitdemo.utils.Constants.BASE_URL
import com.example.apimvvmunitdemo.utils.DefaultDispatcherProvider
import com.example.apimvvmunitdemo.utils.DefaultResourceProvider
import com.example.apimvvmunitdemo.utils.DispatcherProvider
import com.example.apimvvmunitdemo.utils.ResourceProvider
import com.example.apimvvmunitdemo.data.api.ApiKeyInterceptor
import com.example.apimvvmunitdemo.utils.network.DefaultNetworkHelper
import com.example.apimvvmunitdemo.utils.network.NetworkHelper


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = BASE_URL

    @NetworkApiKey
    @Provides
    fun provideApiKey(): String = API_KEY

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(@NetworkApiKey apiKey: String): ApiKeyInterceptor =
        ApiKeyInterceptor(apiKey)

    @Provides
    @Singleton
    fun provideOkHttpClient(apiKeyInterceptor: ApiKeyInterceptor): OkHttpClient =
        OkHttpClient().newBuilder().addInterceptor(apiKeyInterceptor).build()

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NetworkService::class.java)

    }

    @Provides
    @Singleton
    fun provideLogger(): Logger = AppLogger()

    @Provides
    @Singleton
    fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper {
        return DefaultNetworkHelper(context)
    }

    @Provides
    @Singleton
    fun provideStringHelper(@ApplicationContext context: Context): ResourceProvider {
        return DefaultResourceProvider(context)
    }
}