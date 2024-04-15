package com.example.apimvvmunitdemo.navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.apimvvmunitdemo.ui.FullScreenLayout

import com.example.apimvvmunitdemo.ui.HomeScreen
import com.example.apimvvmunitdemo.ui.onlinepaging.TopHeadlinesPagingRoute
import com.example.apimvvmunitdemo.ui.toponlineheading.TopHeadlinesOnlineRoute
import com.mayursarode.newsapp.ui.base.openCustomChromeTab

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    val TAG = "NavHostController"
    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(
            route = Screen.HomeScreen.route
        ) {
            Log.d(TAG, "SetupNavGraph:HomeScreen ")
            HomeScreen(navController)
        }

        composable(
            route = Screen.TopHeadlinesOnline.route
        ) {
            TopHeadlinesOnlineRoute(
                navController = navController,
                onNewsClick = {
                    //   openCustomChromeTab(context, it)
//                    FullScreenLayout(navController,it)
                    navController.navigate(
                        Screen.FullScreenLayout.passData(
                            url = it.imageUrl,
                            description = it.description,
                            title = it.title
                        )
                    )
                })
        }

        composable(
            route = Screen.FullScreenLayout.route,
            arguments = listOf(
                navArgument("url") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("description") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("title") {
                    type = NavType.StringType
                    defaultValue = ""
                }

            )
        ) {
            val url = it.arguments?.getString("url")
            val description = it.arguments?.getString("description")
            val title = it.arguments?.getString("title")

            FullScreenLayout(navController,title, url, description)

        }
        composable(
            route = Screen.TopHeadlinesPagination.route
        ) {
            Log.d(TAG, "SetupNavGraph:  TopHeadlinesPagingRoute")
            TopHeadlinesPagingRoute(
                navController = navController,
                onNewsClick = {
                    openCustomChromeTab(context, it)
                })
        }
    }
}

