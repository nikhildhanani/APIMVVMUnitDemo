package com.example.apimvvmunitdemo.navigation

import com.example.apimvvmunitdemo.data.model.ApiArticle

sealed class Screen(val route: String) {

    object HomeScreen : Screen(route = "HomeScreen")

    object TopHeadlinesOnline : Screen(route = "topHeadlineOnline")

    //    object FullScreenLayout : Screen(route = "FullScreenLayout")
    object FullScreenLayout : Screen(route = "FullScreenLayout?url={url}&description={description}&title={title}") {
        fun passData(
            url: String,
            description: String,
            title: String,
        ): String {
            return "FullScreenLayout?url=$url&description=$description&title=$title"
        }
    }

    object TopHeadlinesOffline : Screen(route = "topHeadlineOffline")

    object TopHeadlinesPagination : Screen(route = "topHeadlinePagination")

    object NewsSources : Screen(route = "newssources")

    object NewsBySources :
        Screen(route = "newsbysources?sourceId={sourceId}&countryCode={countryCode}&languageId={languageId}") {
        fun passData(
            sourceId: String = "",
            countryCode: String = "",
            languageId: String = ""
        ): String {
            return "newsbysources?sourceId=$sourceId&countryCode=$countryCode&languageId=$languageId"
        }
    }

    object Countries : Screen(route = "countries")

    object Languages : Screen(route = "languages")

    object SearchNews : Screen(route = "searchnews")

}
