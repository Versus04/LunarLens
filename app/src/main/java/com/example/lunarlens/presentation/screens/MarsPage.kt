package com.example.lunarlens.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.lunarlens.presentation.viewmodels.homeScreenViewmodel

@Composable
fun marsPage(
    homeScreenViewModel: homeScreenViewmodel
) {
    val marsPhotos = homeScreenViewModel.marsImage.collectAsState()

    when {
        marsPhotos.value.isEmpty() -> {
            // Show loading or empty state
            Text("khaali hai bhaiya")

        }
        else ->
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(marsPhotos.value) { photo ->
            val imrsrc = photo.img_src.substring(0,photo.img_src.length-3)+"jpg"
                Card(Modifier.fillMaxSize()) {


                    val imageUrl = photo.img_src
                        .replace("http://", "https://")
                        .replace(".JPG", ".jpg")

                    println("Attempting to load image from: $imageUrl") // Debug log

                    AsyncImage(
                        model = imageUrl,
                        contentDescription = "Mars photo",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentScale = ContentScale.Crop,
                        onError = {
                            println("Failed to load image: ${it.result.throwable.message}")
                        }
                    )
            }
        }
    }
}}