package com.boa.rickandmortytest.domain.usecase

import androidx.paging.PagingData
import com.boa.rickandmortytest.domain.model.LocationModel
import com.boa.rickandmortytest.domain.model.ResourceModel
import com.boa.rickandmortytest.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

//@ViewModelScoped
class GetLocationListUseCase @Inject constructor(private val locationRepository: LocationRepository) {
    suspend operator fun invoke(): Flow<ResourceModel<PagingData<LocationModel>>> = flow {
        //Connecting with the repository in Data Layer
        emit(ResourceModel.Loading(true))
        locationRepository.getAllLocations().collect {
            try {
                emit(ResourceModel.Success(it))
            } catch (e: Exception) {
                emit(ResourceModel.Error(e.message.toString()))
            }
        }
    }
}