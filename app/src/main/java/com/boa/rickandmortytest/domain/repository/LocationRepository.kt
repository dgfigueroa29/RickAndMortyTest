package com.boa.rickandmortytest.domain.repository

import androidx.paging.PagingData
import com.boa.rickandmortytest.domain.model.LocationModel
import kotlinx.coroutines.flow.Flow

fun interface LocationRepository {
    fun getAllLocations(): Flow<PagingData<LocationModel>>
}
