package com.example.lunarlens.presentation.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lunarlens.presentation.viewmodels.homeScreenViewmodel

@Composable
fun searchScreen( navController: NavController, homeScreenViewmodel: homeScreenViewmodel)
{
    val search  = homeScreenViewmodel.searchField.collectAsState()
    LazyColumn(Modifier.padding(16.dp)){
        item()
        {
            Row(Modifier.fillMaxWidth()){
                OutlinedTextField(leadingIcon = {
                    IconButton(onClick = { homeScreenViewmodel.setBottomBar(true)
                        navController.navigateUp()


                    }){
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                }},
                    label= {Text("Search", style = MaterialTheme.typography.bodyMedium)},value = search.value,
                    onValueChange = {homeScreenViewmodel.updateSearch(it) } ,
                    modifier = Modifier, trailingIcon = {
                        IconButton(onClick = {}) {
                            Icon(
                                Icons.Filled.Search,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }

                    },
                    colors =TextFieldDefaults.outlinedTextFieldColors(
                        textColor = MaterialTheme.colorScheme.onSurface,
                        cursorColor = MaterialTheme.colorScheme.primary,
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                        focusedLabelColor = MaterialTheme.colorScheme.primary,
                        unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    )

            }
        }

    }
}