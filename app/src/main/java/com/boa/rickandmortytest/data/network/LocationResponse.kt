package com.boa.rickandmortytest.data.network

import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(val info: InfoDto?, val results: List<LocationDto>?)
