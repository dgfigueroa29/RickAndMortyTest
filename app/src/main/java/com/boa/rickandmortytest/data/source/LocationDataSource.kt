package com.boa.rickandmortytest.data.source

import com.boa.rickandmortytest.data.network.InfoDto
import com.boa.rickandmortytest.data.network.LocationDto
import com.boa.rickandmortytest.data.network.LocationResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import timber.log.Timber
import javax.inject.Inject

class LocationDataSource @Inject constructor(
    private val api: HttpClient,
) {
    suspend fun getAllLocations(page: Int = 1): LocationResponse {
        Timber.w("***DS calling Ktor")
        val response = api.get("https://rickandmortyapi.com/api/location?page=$page")
                .body<LocationResponse>()

        return LocationResponse(
            InfoDto(1, 1, "next", "prev"),
            listOf(LocationDto(0, "name", "type", "dimension", listOf("1", "2"), "url", "created"))
        )
    }
}