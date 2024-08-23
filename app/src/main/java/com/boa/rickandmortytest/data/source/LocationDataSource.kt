package com.boa.rickandmortytest.data.source

import com.boa.rickandmortytest.data.network.InfoDto
import com.boa.rickandmortytest.data.network.LocationDto
import com.boa.rickandmortytest.data.network.LocationResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import timber.log.Timber

class LocationDataSource {
    suspend fun getAllLocations(page: Int = 1): LocationResponse {
        Timber.w("***DS calling Ktor")
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(Logging) {
                level = LogLevel.ALL
            }
        }
        try {
            val response = client.get("https://rickandmortyapi.com/api/location?page=$page")
                .body<LocationResponse>()
        } catch (e: Exception) {
            Timber.e("KTOR ERROR: ${e.stackTraceToString()}")
        }

        return LocationResponse(
            InfoDto(1, 1, "next", "prev"),
            listOf(LocationDto(0, "name", "type", "dimension", listOf("1", "2"), "url", "created"))
        )
    }
}