package com.boa.rickandmortytest.data.source


import com.boa.rickandmortytest.data.network.InfoDto
import com.boa.rickandmortytest.data.network.LocationDto
import com.boa.rickandmortytest.data.network.LocationResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocationDataSource @Inject constructor(
    private val api: HttpClient,
) {
    suspend fun getAllLocations(page: Int = 1): LocationResponse {
        return withContext(Dispatchers.IO) {
            var dto = LocationResponse(
                InfoDto(0, 1, "next", "prev"),
                listOf(
                    LocationDto(
                        0,
                        "name",
                        "type",
                        "dimension",
                        listOf("1", "2"),
                        "url",
                        "created"
                    )
                )
            )
            val response =
                api.get(urlString = "https://rickandmortyapi.com/api/location?page=$page")
            when (response.status.value) {
                in 200..299 -> {
                    dto = response.body<LocationResponse>()
                }
            }

            dto
        }
    }
}