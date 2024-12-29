package com.example.lunarlens.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lunarlens.data.remote.dto.planetDTO
import com.example.lunarlens.data.remote.dto.planetDTOItem

@Composable
fun planetCard(planetDTOItem: planetDTOItem)
{
    Card(Modifier.width(200.dp).padding(end=8.dp) , colors = CardColors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.secondary,
        disabledContainerColor = MaterialTheme.colorScheme.tertiary,
        disabledContentColor = MaterialTheme.colorScheme.surfaceVariant
    )) {
        Column(Modifier.fillMaxSize().padding(8.dp), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text(planetDTOItem.name)
            Text(planetDTOItem.radius.toString()+" Jupiters")
            Text(planetDTOItem.mass.toString()+" Jupiters")
            Text(planetDTOItem.distance_light_year.toString()+ "Light Years")
        }
    }
}