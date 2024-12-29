package com.example.lunarlens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lunarlens.presentation.screens.HomeScreen
import com.example.lunarlens.presentation.screens.MainScreen
import com.example.lunarlens.presentation.viewmodels.homeScreenViewmodel
import com.example.lunarlens.ui.theme.LunarLensTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val homeScreenViewmodel : homeScreenViewmodel by  viewModels()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LunarLensTheme {
                MainScreen(homeScreenViewmodel)
            }
        }
    }
}

