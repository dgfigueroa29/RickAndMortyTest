package com.boa.rickandmortytest.data.network

import kotlinx.serialization.Serializable

@Serializable
data class InfoDto(
    val count: Int? = null, val pages: Int? = null, val next: String? = null,
    val prev: String? = null
)
