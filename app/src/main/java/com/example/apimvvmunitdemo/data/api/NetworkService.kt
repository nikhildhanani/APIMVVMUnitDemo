package com.example.apimvvmunitdemo.data.api

import com.example.apimvvmunitdemo.data.model.TopHeadlinesResponse
import com.example.apimvvmunitdemo.utils.Constants.DEFAULT_COUNTRY
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String = DEFAULT_COUNTRY): TopHeadlinesResponse

    @GET("top-headlines")
    suspend fun getTopHeadlinesPaging(
        @Query("country") country: String = DEFAULT_COUNTRY,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): TopHeadlinesResponse


    /*
    @GET("top-headlines/sources")
    suspend fun getNewsSources(): NewsSourcesResponse

    @GET("top-headlines")
    suspend fun getNewsBySources(@Query("sources") sources: String): TopHeadlinesResponse

    @GET("top-headlines")
    suspend fun getNewsByCountry(@Query("country") country: String): TopHeadlinesResponse

    @GET("top-headlines")
    suspend fun getNewsByLanguage(@Query("language") language: String): TopHeadlinesResponse


    @GET("everything")
    suspend fun getNewsBySearch(@Query("q") queries: String): TopHeadlinesResponse*/

}