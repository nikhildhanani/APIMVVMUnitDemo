package com.example.apimvvmunitdemo.ui.onlinepaging

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.apimvvmunitdemo.utils.logger.Logger
import com.example.apimvvmunitdemo.data.model.ApiArticle
import com.example.apimvvmunitdemo.data.repository.TopHeadlinesRepository
import com.example.apimvvmunitdemo.ui.base.BaseViewModel
import com.example.apimvvmunitdemo.utils.DispatcherProvider
import com.example.apimvvmunitdemo.utils.ResourceProvider
import com.example.apimvvmunitdemo.utils.network.NetworkHelper

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopHeadlinesPagingViewModel @Inject constructor(
    private val topHeadlinesRepository: TopHeadlinesRepository,
    private val networkHelper: NetworkHelper,
    private val dispatcherProvider: DispatcherProvider,
    private val resourceProvider: ResourceProvider,
    private val logger: Logger
) : BaseViewModel() {
    companion object {
        const val TAG = "TopHeadlinesPagingViewModel"
    }

    private val _uiState = MutableStateFlow<PagingData<ApiArticle>>(value = PagingData.empty())

    val uiState: StateFlow<PagingData<ApiArticle>> = _uiState

    init {
        fetchNewsPaging()
    }

    private fun fetchNewsPaging() {
        viewModelScope.launch {
            if (!networkHelper.isNetworkConnected()) {
                return@launch
            } else {
                topHeadlinesRepository.getTopHeadlinesPgn()
                    .flowOn(dispatcherProvider.io)
                    .collect {
                        _uiState.value = it
                        logger.d(TAG, it.toString())
                    }
            }
        }
    }
}