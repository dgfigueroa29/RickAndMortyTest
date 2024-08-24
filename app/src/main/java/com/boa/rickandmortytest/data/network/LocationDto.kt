package com.boa.rickandmortytest.data.network

import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    val id: Int? = null,
    val name: String? = null,
    val type: String? = null,
    val dimension: String? = null,
    val residents: Set<String>? = emptySet(),
    val url: String? = null,
    val created: String? = null
)