package com.boa.rickandmortytest.data.network

import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(
    val info: InfoDto? = null,
    val results: List<LocationDto>? = emptyList()
)
