package com.example.lunarlens.presentation.screens

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.lunarlens.R
import com.example.lunarlens.data.remote.dto.Item
import com.example.lunarlens.utils.Screens

@Composable
fun DetailPage(item : Screens.DetailsPage , onclick: ()-> Unit)
{
    LazyColumn(
        Modifier
            .systemBarsPadding()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        item()
        {
            Row(Modifier.fillMaxWidth() ,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
                IconButton(onClick = {
                    onclick()
                }) {
                    Icon(imageVector = Icons.Filled.ArrowBack , contentDescription = null)

                }
                Text(text=item.title , style = MaterialTheme.typography.headlineLarge,
                    modifier =  Modifier.basicMarquee() , textAlign = TextAlign.Start)
            }
        }
        item()
        {

            Card(Modifier.clip(RoundedCornerShape(8.dp))){ AsyncImage(model = item.hdurl, contentDescription = item.title) }
            Spacer(Modifier.height(8.dp))

        }
        item()
        {
            Column {
                Text(text = item.title ,style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(4.dp))
                Text(text = item.description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}