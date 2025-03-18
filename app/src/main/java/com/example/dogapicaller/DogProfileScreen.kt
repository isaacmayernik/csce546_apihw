package com.example.dogapicaller

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

@Composable
fun DogProfileScreen(viewModel: DogViewModel = viewModel()) {
    val dog by viewModel.dog.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        when {
            loading -> {
                CircularProgressIndicator()
            }

            error != null -> {
                Text(text = error!!, fontSize = 16.sp, color = Color.Red)
                Button(onClick = { viewModel.fetchRandomDog() }) {
                    Text(text = "Retry")
                }
            }

            dog != null -> {
                val aspectRatio = dog!!.width.toFloat() / dog!!.height.toFloat()
                AsyncImage(
                    model = dog!!.url,
                    contentDescription = "Dog Image",
                    modifier = Modifier
                        .fillMaxSize(0.8f)
                        .aspectRatio(aspectRatio),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = { viewModel.fetchRandomDog() }) {
                    Text(text = "Load New Dog")
                }
            }
        }
    }
}