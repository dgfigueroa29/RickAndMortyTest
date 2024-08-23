package com.boa.rickandmortytest.di

import com.boa.rickandmortytest.data.source.LocationDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DataSourceModule {
    @Provides
    fun provideLocationDataSource(): LocationDataSource = LocationDataSource()
}