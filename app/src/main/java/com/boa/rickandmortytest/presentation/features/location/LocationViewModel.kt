package com.boa.rickandmortytest.presentation.features.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boa.rickandmortytest.domain.usecase.GetLocationListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(private val getLocationListUseCase: GetLocationListUseCase) :
    ViewModel() {
    val locationState = LocationState()

    /**
     * Get Location List from UseCase in Domain Layer
     */
    fun getLocations() {
        viewModelScope.launch {
            refreshLoading(true)
            getLocationListUseCase.invoke().collect { resource ->
                Timber.w(
                    "***VM Resource collect flag: ${resource.flag} " +
                            "message: ${resource.message} data: ${resource.data}"
                )
                refreshLoading(resource.flag)
                refreshError(resource.message)

                if (resource.message.isNotEmpty()) {
                    return@collect
                }

                resource.data?.let {
                    refreshLoading(false)
                    locationState.locationList.value = it
                }
            }
        }
    }

    private fun refreshError(message: String) {
        locationState.errorState.value = message
    }

    private fun refreshLoading(flag: Boolean) {
        locationState.loadingState.value = flag
    }
}