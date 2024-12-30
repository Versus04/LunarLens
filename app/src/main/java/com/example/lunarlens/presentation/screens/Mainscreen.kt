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
import com.example.lunarlens.utils.RocketTakeoff
import com.example.lunarlens.utils.ScreenList
import com.example.lunarlens.utils.Screens

//data class Screens(val name: String  , val icon : ImageVector , val route : String)
@Composable
fun MainScreen(homeScreenViewmodel: homeScreenViewmodel)
{
    val showBottomBar = homeScreenViewmodel.showBottomBar.collectAsState()
    /*val screens = listOf<Screens>(Screens("Home" , Icons.Filled.Home , "home_screen"),
        Screens("Search" , Icons.Filled.Search,"search_screen"),
        Screens("Mars" , RocketTakeoff,"mars_screen")
        )*/
    val selectedScreen = remember { mutableStateOf(0) }
    val navController = rememberNavController()
    Scaffold(bottomBar = {
        if(showBottomBar.value){
        NavigationBar() {
            NavigationBarItem(
                selected = false,
                onClick ={navController.navigate(Screens.Home)},
                icon = {Icon(Icons.Filled.Home , contentDescription = null)},

                label = {Text("Home")}
            )
            NavigationBarItem(
                selected = false,
                onClick ={navController.navigate(Screens.Search)

                         },
                icon = {Icon(Icons.Filled.Search , contentDescription = null)},

                label = {Text("Search")}
            )
            NavigationBarItem(
                selected = false,
                onClick ={navController.navigate(Screens.Mars)

                },
                icon = {Icon(Icons.Filled.LocationOn , contentDescription = null)},

                label = {Text("Mars")}
            )
            }
        }}
    ) { paddinvalues ->
        NavHost(startDestination = Screens.Home ,
            navController = navController,
            modifier = Modifier.padding(paddinvalues)) {

            composable<Screens.Home>{
                HomeScreen(homeScreenViewmodel)
            }
            composable<Screens.Search>
            {
                searchScreen(navController , homeScreenViewmodel)
            }
            composable<Screens.Mars>{
                marsPage(homeScreenViewmodel)
            }

            /*composable(ScreenList.HomeScreen.route)
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

        */}

    }
}