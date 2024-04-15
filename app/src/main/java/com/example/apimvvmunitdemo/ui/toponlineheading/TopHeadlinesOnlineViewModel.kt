package com.example.apimvvmunitdemo.ui.toponlineheading

import androidx.lifecycle.viewModelScope
import com.example.apimvvmunitdemo.utils.logger.Logger
import com.example.apimvvmunitdemo.data.model.ApiArticle
import com.example.apimvvmunitdemo.data.repository.TopHeadlinesRepository
import com.example.apimvvmunitdemo.ui.base.BaseViewModel
import com.example.apimvvmunitdemo.ui.base.UiState
import com.example.apimvvmunitdemo.utils.Constants
import com.example.apimvvmunitdemo.utils.DispatcherProvider
import com.example.apimvvmunitdemo.utils.ResourceProvider
import com.example.apimvvmunitdemo.utils.network.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Nikhil Dhanani
 */
@HiltViewModel
class TopHeadlinesOnlineViewModel @Inject constructor(
    private val topHeadlinesRepository: TopHeadlinesRepository,
    private val networkHelper: NetworkHelper,
    private val dispatcherProvider: DispatcherProvider,
    private val resourceProvider: ResourceProvider,
    private val logger: Logger
) : BaseViewModel(){
    companion object {
        const val TAG = "TopHeadlineOnlineViewModel"
    }

    private val _uiState = MutableStateFlow<UiState<List<ApiArticle>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<ApiArticle>>> = _uiState

    init {
        fetchNewsOnline()
    }

    fun fetchNewsOnline() {
        viewModelScope.launch(dispatcherProvider.main) {
            if (!networkHelper.isNetworkConnected()) {
                val errorText = resourceProvider.getStringNoInternetAvailable()
                _uiState.value = UiState.Error(errorText)
                return@launch
            } else {
                topHeadlinesRepository.getTopHeadlinesOnline(Constants.DEFAULT_COUNTRY)
                    .flowOn(dispatcherProvider.io)
                    .catch { e ->
                        logger.d(TAG, "fetchNews: ${e.message.toString()}")
                        _uiState.value = UiState.Error(e.toString())
                    }.collect {
                        logger.d(TAG, it.toString())
                        _uiState.value = UiState.Success(it)
                    }
            }
        }
    }
}