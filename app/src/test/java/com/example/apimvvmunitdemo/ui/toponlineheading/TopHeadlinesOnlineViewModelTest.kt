package com.example.apimvvmunitdemo.ui.toponlineheading

import app.cash.turbine.test
import com.example.apimvvmunitdemo.utils.dispacher.TestDispatcherProvider
import com.example.apimvvmunitdemo.data.model.ApiArticle
import com.example.apimvvmunitdemo.data.repository.TopHeadlinesRepository
import com.example.apimvvmunitdemo.utils.Constants.DEFAULT_COUNTRY
import com.example.apimvvmunitdemo.utils.DispatcherProvider
import com.example.apimvvmunitdemo.utils.ResourceProvider
import com.example.apimvvmunitdemo.ui.base.UiState
import com.mayursarode.newsapp.utils.logger.Logger
import com.mayursarode.newsapp.utils.network.NetworkHelper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Nikhil Dhanani
 */

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TopHeadlinesOnlineViewModelTest {

    @Mock
    private lateinit var topHeadlinesRepository: TopHeadlinesRepository


    private lateinit var dispatcherProvider: DispatcherProvider

    @Mock
    private lateinit var networkHelper: NetworkHelper

    @Mock
    private lateinit var resourceProvider: ResourceProvider

    @Mock
    private lateinit var logger: Logger

    @Before
    fun setup() {
        dispatcherProvider = TestDispatcherProvider()
    }

    @Test
    fun fetchNewsOnline_whenRepositoryResponseSuccess_shouldSetSuccessUiState() {
        runTest {
            doReturn(flowOf(emptyList<ApiArticle>()))
                .`when`(topHeadlinesRepository)
                .getTopHeadlinesOnline(DEFAULT_COUNTRY)

            val viewModel = TopHeadlinesOnlineViewModel(
                topHeadlinesRepository = topHeadlinesRepository,
                networkHelper = networkHelper,
                dispatcherProvider = dispatcherProvider,
                resourceProvider = resourceProvider,
                logger = logger
            )
            viewModel.uiState.test {
                assertEquals(UiState.Success(emptyList<List<ApiArticle>>()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(topHeadlinesRepository, times(1)).getTopHeadlinesOnline(DEFAULT_COUNTRY)
        }
    }

    @Test
    fun fetchNewsOnline_whenRepositoryResponseError_shouldSetErrorUiState() {
        runTest {
            val errorMessage = "Error Message For You"
            doReturn(flow<List<ApiArticle>> {
                throw IllegalStateException(errorMessage)
            })
                .`when`(topHeadlinesRepository)
                .getTopHeadlinesOnline(DEFAULT_COUNTRY)

            val viewModel = TopHeadlinesOnlineViewModel(
                topHeadlinesRepository = topHeadlinesRepository,
                networkHelper = networkHelper,
                dispatcherProvider = dispatcherProvider,
                resourceProvider = resourceProvider,
                logger = logger
            )
            viewModel.uiState.test {
                assertEquals(
                    UiState.Error(IllegalStateException(errorMessage).toString()), awaitItem()
                )
                cancelAndIgnoreRemainingEvents()
            }

        }
    }

    @After
    fun tearDown() {
        // do something if required
    }
}