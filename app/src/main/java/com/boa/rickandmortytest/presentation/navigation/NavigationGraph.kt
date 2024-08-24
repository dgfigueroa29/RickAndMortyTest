package com.boa.rickandmortytest.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.boa.rickandmortytest.presentation.features.location.LocationScreen

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.LOCATION.endpoint,
    ) {
        composable(Screen.LOCATION.endpoint) {
            LocationScreen()
        }
    }
}