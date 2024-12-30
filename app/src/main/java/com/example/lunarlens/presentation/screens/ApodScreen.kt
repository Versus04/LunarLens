package com.example.lunarlens.presentation.screens

import com.example.lunarlens.R
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.lunarlens.utils.Screens
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController

@Composable
fun apodDetails( navController: NavController,apod: Screens.apod)
{
    LazyColumn(Modifier.padding(start = 16.dp , end = 16.dp)) {
        item()
        {
            Row(Modifier.fillMaxWidth() ,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
                IconButton(onClick = {navController.popBackStack()}) {
                    Icon(imageVector = Icons.Filled.ArrowBack , contentDescription = null)

                }
                Text(stringResource(R.string.apod) , style = MaterialTheme.typography.headlineLarge,
                   modifier =  Modifier.basicMarquee())
            }
        }
        item()
        {
            AsyncImage(apod.hdurl,contentDescription =apod.title , Modifier.clip(RoundedCornerShape(8.dp)))
            Spacer(Modifier.height(8.dp))
        }
        item()
        {
            Column()
            {
                Text(apod.title , style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(4.dp))
                Text(apod.explanation , style = MaterialTheme.typography.bodyMedium)
                Text("Copyright:${apod.copyright}" , style = MaterialTheme.typography.bodySmall)
            }
        }

    }

}