package com.example.apimvvmunitdemo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.apimvvmunitdemo.utils.Constants
import com.mayursarode.newsapp.ui.base.topAppBar


/*@Composable
fun FullScreenLayoutRoute(
    s:String,
    viewModel: TopHeadlinesOnlineViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            topAppBar(
                title = Constants.TOP_HEADLINES_ONLINE, showBackArrow = true
            ) { navController.popBackStack() }
        },
        content = { padding ->
            FullScreenLayout(padding,s)

        }
    )
}*/


@Composable
fun FullScreenLayout(
    navController: NavHostController,
    model: String?,
    imageUrl: String?,
    description: String?
) {

    Scaffold(
        topBar = {
            topAppBar(
                title = Constants.TOP_HEADLINES_ONLINE, showBackArrow = true
            ) { navController.popBackStack() }
        },
        content = { padding ->
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
//            painter = painterResource(id = R.drawable.def),
            rememberImagePainter(imageUrl),
            contentDescription = "Your Image",
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = model!!,
            color = Color.Black,
            modifier = Modifier.padding(5.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = description!!,
            color = Color.Black,
            modifier = Modifier.padding(8.dp)
        )
    }
}

/*
@Preview(showBackground = true)
@Composable
fun PreviewFullScreenLayout() {
    FullScreenLayout( "N")
}*/
