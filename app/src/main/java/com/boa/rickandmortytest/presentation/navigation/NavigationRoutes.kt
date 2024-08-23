package com.boa.rickandmortytest.presentation.navigation

@Suppress("SameParameterValue")
sealed class NavigationRoutes(val endpoint: String) {
    data object LocationRoute : NavigationRoutes("location")
}