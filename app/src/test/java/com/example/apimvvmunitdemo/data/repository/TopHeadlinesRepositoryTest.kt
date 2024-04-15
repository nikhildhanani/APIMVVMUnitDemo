package com.example.apimvvmunitdemo.data.repository

import app.cash.turbine.test
import com.example.apimvvmunitdemo.data.model.ApiArticle
import com.example.apimvvmunitdemo.data.model.ApiSource
import com.example.apimvvmunitdemo.data.model.TopHeadlinesResponse
import com.example.apimvvmunitdemo.utils.Constants.DEFAULT_COUNTRY
import com.example.apimvvmunitdemo.data.api.NetworkService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.doThrow
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


/**
 * Created by Nikhil Dhanani
 */

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TopHeadlinesRepositoryTest {

    @Mock
    private lateinit var networkService: NetworkService

    private lateinit var topHeadlinesRepository: TopHeadlinesRepository

    @Before
    fun setup() {
        topHeadlinesRepository = TopHeadlinesRepository(networkService)
    }

    @Test
    fun getTopHeadlinesOnline_whenNetworkServiceResponseSuccess_shouldReturnSuccess() {
        runTest {
            val source = ApiSource(id = "sourceId", name = "sourceName1")
            val article = ApiArticle(
                title = "title",
                description = "description",
                url = "url",
                imageUrl = "imageUrl",
                source = source
            )

            val articles = mutableListOf<ApiArticle>()
            articles.add(article)

            val topHeadlinesResponse = TopHeadlinesResponse(
                status = "ok", totalResults = 1, articles = articles
            )

            doReturn(topHeadlinesResponse).`when`(networkService).getTopHeadlines(DEFAULT_COUNTRY)

            topHeadlinesRepository.getTopHeadlinesOnline(DEFAULT_COUNTRY).test {
                assertEquals(topHeadlinesResponse.articles, awaitItem())
                cancelAndIgnoreRemainingEvents()
            }

            verify(networkService, times(1)).getTopHeadlines(DEFAULT_COUNTRY)

        }
    }

    @Test
    fun getTopHeadlinesOnline_whenNetworkServiceResponseError_shouldReturnError() {
        runTest {
            val errorMessage = "Error Message"
            doThrow(RuntimeException(errorMessage)).`when`(networkService).getTopHeadlines(
                DEFAULT_COUNTRY
            )
            topHeadlinesRepository.getTopHeadlinesOnline(DEFAULT_COUNTRY).test {
                assertEquals(errorMessage, awaitError().message)
                cancelAndIgnoreRemainingEvents()
            }
            verify(networkService, times(1)).getTopHeadlines(DEFAULT_COUNTRY)
        }
    }

    @After
    fun tearDown() {

    }
}