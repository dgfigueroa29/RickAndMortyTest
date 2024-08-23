package com.boa.rickandmortytest.di

import com.boa.rickandmortytest.data.repository.LocationRepositoryImpl
import com.boa.rickandmortytest.data.source.LocationDataSource
import com.boa.rickandmortytest.domain.repository.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {
    @Provides
    fun provideLocationRepository(dataSource: LocationDataSource): LocationRepository =
        LocationRepositoryImpl(dataSource)
}