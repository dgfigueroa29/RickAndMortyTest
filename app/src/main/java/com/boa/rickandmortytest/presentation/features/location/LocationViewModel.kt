package com.boa.rickandmortytest.presentation.features.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boa.rickandmortytest.domain.usecase.GetLocationListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val getLocationListUseCase: GetLocationListUseCase
) :
    ViewModel() {
    val locationState = LocationState()
    private var isConnected = true

    fun updateConnectionStatus(isConnected: Boolean) {
        refreshLoading(true)
        this.isConnected = isConnected

        if (isConnected) {
            refreshError("")
            getLocations()
        } else {
            refreshLoading(false)
            refreshError("No data to display. Please restart your connection or your app to continue.\n")
        }
    }

    /**
     * Get Location List from UseCase in Domain Layer
     */
    private fun getLocations() {
        //Force loading at the beginning
        viewModelScope.launch {
            getLocationListUseCase.invoke().collect { resource ->
                if (resource.data != null && resource.message.isBlank()) {
                    locationState.setList(resource.data)
                    return@collect
                }

                if (resource.message.isNotBlank() && resource.data == null) {
                    refreshError(resource.message)
                    refreshLoading(false)
                    return@collect
                }
            }
        }
    }

    fun refreshError(message: String) {
        locationState.setError(message)
    }

    fun refreshLoading(flag: Boolean) {
        locationState.setLoading(flag)
    }
}
