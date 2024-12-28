package com.boa.rickandmortytest.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.boa.rickandmortytest.presentation.navigation.NavigationGraph
import com.boa.rickandmortytest.presentation.theme.RickAndMortyTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            RickAndMortyTestTheme {
                Scaffold { padding ->
                    navController = rememberNavController()
                    NavigationGraph(
                        modifier = Modifier.padding(padding),
                        navController = navController
                    )
                }
            }
        }
    }
}
