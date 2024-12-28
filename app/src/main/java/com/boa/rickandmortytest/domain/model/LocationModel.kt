package com.boa.rickandmortytest.domain.model

data class LocationModel(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residentUrls: Set<String>,
    val url: String,
    val created: String
)
