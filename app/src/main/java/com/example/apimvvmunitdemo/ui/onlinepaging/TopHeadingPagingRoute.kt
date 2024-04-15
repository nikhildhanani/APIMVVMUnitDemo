package com.example.apimvvmunitdemo.ui.onlinepaging

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.apimvvmunitdemo.data.model.ApiArticle
import com.example.apimvvmunitdemo.utils.Constants
import com.mayursarode.newsapp.ui.base.Article
import com.mayursarode.newsapp.ui.base.ShowError
import com.mayursarode.newsapp.ui.base.ShowLoading
import com.mayursarode.newsapp.ui.base.topAppBar

/**
 * Created by Nikhil Dhanani
 */

@Composable
fun TopHeadlinesPagingRoute(
    onNewsClick: (url: ApiArticle) -> Unit,
    viewModel: TopHeadlinesPagingViewModel = hiltViewModel(),
    navController: NavController
){
    val articles = viewModel.uiState.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            topAppBar(
                title = Constants.TOP_HEADLINES_PAGING, showBackArrow = true
            ) { navController.popBackStack() }
        },
        content = { padding ->
            TopHeadlinesPagingScreen(padding, articles, onNewsClick)
        }
    )
}

@Composable
fun TopHeadlinesPagingScreen(
    padding: PaddingValues,
    articles: LazyPagingItems<ApiArticle>,
    onNewsClick: (url: ApiArticle) -> Unit
) {

    Column(modifier = Modifier.padding(padding)) {

        ArticleListPgn(articles, onNewsClick)

        articles.apply {

            when {
                loadState.refresh is LoadState.Loading -> {
                    ShowLoading()
                }

                loadState.refresh is LoadState.Error -> {
                    val error = articles.loadState.refresh as LoadState.Error
                    ShowError(text = error.error.localizedMessage!!)
                }

                loadState.append is LoadState.Loading -> {
                    ShowLoading()
                }

                loadState.append is LoadState.Error -> {
                    val error = articles.loadState.refresh as LoadState.Error
                    ShowError(text = error.error.localizedMessage!!)
                }
            }
        }
    }
}

@Composable
fun ArticleListPgn(
    articles: LazyPagingItems<ApiArticle>,
    onNewsClick: (url: ApiArticle) -> Unit
) {
    LazyColumn {
        items(articles.itemCount, key = { index -> articles[index]!!.url }) { index ->
            Article(article = articles[index]!!, onNewsClick = onNewsClick)

        }
    }
}