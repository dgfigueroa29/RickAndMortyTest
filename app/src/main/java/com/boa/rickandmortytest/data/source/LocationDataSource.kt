package com.boa.rickandmortytest.data.source


import com.boa.rickandmortytest.data.network.InfoDto
import com.boa.rickandmortytest.data.network.LocationResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocationDataSource @Inject constructor(
    private val httpClient: HttpClient,
) {
    suspend fun getAllLocations(page: Int = 1): LocationResponse =
        withContext(Dispatchers.IO) {
            // Directly fetch data
            val response =
                httpClient.get("https://rickandmortyapi.com/api/location?page=$page")// Handle potential errors, don't just assume success
            if (response.status.value in 200..299) {
                response.body<LocationResponse>()
            } else {
                // Handle error cases, maybe throw an exception or return an empty result
                LocationResponse(InfoDto(0, 1, "next", "prev"), emptyList())
            }
        }
}