package com.boa.rickandmortytest.di

import com.boa.rickandmortytest.data.source.LocationDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Suppress("MagicNumber")
@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideService(): HttpClient {
        return HttpClient(Android) {
            install(Logging) {
                //Use ALL for debugging purposes
                level = LogLevel.NONE
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
            engine {
                connectTimeout = 10_000
                socketTimeout = 10_000
            }
        }
    }

    @Provides
    @Singleton
    fun provideLocationDataSource(httpClient: HttpClient): LocationDataSource =
        LocationDataSource(httpClient)
}
