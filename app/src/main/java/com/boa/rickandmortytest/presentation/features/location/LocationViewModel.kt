package com.boa.rickandmortytest.presentation.features.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boa.rickandmortytest.domain.usecase.GetLocationListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(private val getLocationListUseCase: GetLocationListUseCase) :
    ViewModel() {
    val locationState = LocationState()

    /**
     * Get Location List from UseCase in Domain Layer
     */
    fun getLocations() {
        //Force loading at the beginning
        refreshLoading(true)
        viewModelScope.launch {
            getLocationListUseCase.invoke().collect { resource ->
                if (resource.data != null && resource.message.isBlank()) {
                    locationState.locationList.value = resource.data
                    return@collect
                }

                if (resource.message.isNotBlank() && resource.data == null) {
                    refreshError(resource.message)
                    return@collect
                }
            }
        }
    }

    fun refreshError(message: String) {
        locationState.errorState.value = message
        refreshLoading(message.isNotBlank())
    }

    private fun refreshLoading(flag: Boolean) {
        locationState.loadingState.value = flag
    }
}