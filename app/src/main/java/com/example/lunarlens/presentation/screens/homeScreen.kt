package com.example.lunarlens.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lunarlens.R
import coil3.compose.AsyncImage
import com.example.lunarlens.presentation.viewmodels.homeScreenViewmodel
import com.example.lunarlens.utils.Screens
import com.example.lunarlens.utils.planetCard

@Composable
fun HomeScreen(homeScreenViewmodel: homeScreenViewmodel , navController: NavController)
{
    val planetData = homeScreenViewmodel.planetdata.collectAsState()
    val apodData  = homeScreenViewmodel.apod.collectAsState()
    val imgsrc = apodData.value.hdurl
    val title = apodData.value.title
    val exp = apodData.value.explanation
    val copy = apodData.value.copyright
    LazyColumn(
        Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface)
            .statusBarsPadding()
            .padding(16.dp)) {
        item()
        {

            Column(Modifier.fillMaxWidth()

            ) {

                Card(Modifier.clickable(
                    onClick = {
                       navController.navigate(Screens.apod(hdurl = imgsrc ,
                           title = title , explanation = exp ,
                           copyright = copy))
                    }
                )){

                    AsyncImage(
                        model = apodData.value.url,
                        contentDescription = null,
                        Modifier.height(200.dp).width(400.dp).clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(Modifier.height(8.dp))
                Text(text = stringResource(R.string.apod),
                    style = MaterialTheme.typography.headlineSmall ,
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(8.dp))
                Text(text = stringResource(R.string.apodDescription) ,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(Modifier.height(8.dp))

            }

        }
        item()
        {
            LazyRow()
            {
                items(planetData.value)
                {  planets->
                    planetCard(planets)
                }
            }
        }
    }
}

