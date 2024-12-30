package com.example.lunarlens.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lunarlens.presentation.viewmodels.homeScreenViewmodel
import com.example.lunarlens.utils.ScreenList

data class Screens(val name: String  , val icon : ImageVector , val route : String)
@Composable
fun MainScreen(homeScreenViewmodel: homeScreenViewmodel)
{
    val showBottomBar = homeScreenViewmodel.showBottomBar.collectAsState()
    val screens = listOf<Screens>(Screens("Home" , Icons.Filled.Home , "home_screen"),
        Screens("Search" , Icons.Filled.Search,"search_screen"),
        Screens("Mars" , Icons.Filled.LocationOn,"mars_screen")
        )
    val selectedScreen = remember { mutableStateOf(0) }
    val navController = rememberNavController()
    Scaffold(bottomBar = {
        if(showBottomBar.value){
        NavigationBar() {
            screens.forEachIndexed {index, screens->
                NavigationBarItem(
                    selected = selectedScreen.value==index,
                    onClick = {selectedScreen.value = index
                              navController.navigate(screens.route)},
                    icon = {Icon(screens.icon , contentDescription = null)},
                    colors = NavigationBarItemColors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        selectedIndicatorColor = MaterialTheme.colorScheme.primaryContainer,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledIconColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f),
                        disabledTextColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f),

                    )
                    ,
                    label = { Text(screens.name) },
                    
                )
            }
        }}
    }
    ) { paddinvalues ->
        NavHost(startDestination = ScreenList.HomeScreen.route ,
            navController = navController,
            modifier = Modifier.padding(paddinvalues)) {
                composable(ScreenList.HomeScreen.route)
                {
                    HomeScreen(homeScreenViewmodel)
                }
                composable(ScreenList.SearchScreen.route)
                {
                    DisposableEffect(Unit) {
                        homeScreenViewmodel.setBottomBar(false)
                        onDispose {
                            homeScreenViewmodel.setBottomBar(true)
                        }

                    }
                    searchScreen(navController,homeScreenViewmodel)
                }
            composable(ScreenList.MarsScreen.route)
            {
                marsPage(homeScreenViewmodel)
            }
        }

    }
}