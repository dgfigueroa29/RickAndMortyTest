package com.boa.rickandmortytest.domain.usecase

import androidx.paging.PagingData
import com.boa.rickandmortytest.domain.model.LocationModel
import com.boa.rickandmortytest.domain.model.UiStateModel
import com.boa.rickandmortytest.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Suppress("TooGenericExceptionCaught")
class GetLocationListUseCase @Inject constructor(private val locationRepository: LocationRepository) {
    operator fun invoke(): Flow<UiStateModel<PagingData<LocationModel>>> = flow {
        //Connecting with the repository in Data Layer
        emit(UiStateModel.Loading(true))
        locationRepository.getAllLocations().collect {
            try {
                emit(UiStateModel.Success(it))
            } catch (e: Exception) {
                emit(UiStateModel.Error(e.message ?: "An unknown error occurred"))
            }
        }
    }
}
