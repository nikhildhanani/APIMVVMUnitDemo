package com.example.apimvvmunitdemo.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.apimvvmunitdemo.data.model.ApiArticle
import com.example.apimvvmunitdemo.utils.Constants.PAGE_SIZE
import com.example.apimvvmunitdemo.data.api.NetworkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Nikhil Dhanani
 */
class TopHeadlinesRepository @Inject constructor(
    private val networkService: NetworkService
){

    fun getTopHeadlinesOnline(country: String): Flow<List<ApiArticle>> {
        return flow {
            emit(networkService.getTopHeadlines(country))
        }.map {
            Log.d("TAG", "Nikhil${it.toString()}")
            it.articles
        }
    }

    fun getTopHeadlinesPgn(): Flow<PagingData<ApiArticle>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE
            ),
            pagingSourceFactory = {
                TopHeadlinesPagingSource(networkService)
            }
        ).flow
    }

}