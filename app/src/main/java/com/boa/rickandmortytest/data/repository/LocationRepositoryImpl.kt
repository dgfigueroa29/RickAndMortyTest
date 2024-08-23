package com.boa.rickandmortytest.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.boa.rickandmortytest.data.source.LocationDataSource
import com.boa.rickandmortytest.data.source.LocationPaging
import com.boa.rickandmortytest.domain.model.LocationModel
import com.boa.rickandmortytest.domain.repository.LocationRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class LocationRepositoryImpl @Inject constructor(private val dataSource: LocationDataSource) :
    LocationRepository {
    override fun getAllLocations(): Flow<PagingData<LocationModel>> {
        //Detecting available source of data and prepare it for use
        return Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = { LocationPaging(dataSource) }).flow
    }
}