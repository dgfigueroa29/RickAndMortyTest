package com.boa.rickandmortytest.presentation.features.location

import androidx.paging.PagingData
import com.boa.rickandmortytest.domain.model.LocationModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class LocationState(
    private val _loadingState: MutableStateFlow<Boolean> = MutableStateFlow(false),
    val loadingState: StateFlow<Boolean> = _loadingState.asStateFlow(),
    private val _errorState: MutableStateFlow<String> = MutableStateFlow(""),
    val errorState: StateFlow<String> = _errorState.asStateFlow(),
    private val _locationList: MutableStateFlow<PagingData<LocationModel>> = MutableStateFlow(
        PagingData.empty()
    ),
    val locationList: StateFlow<PagingData<LocationModel>> = _locationList.asStateFlow()
) {
    fun setList(data: PagingData<LocationModel>) {
        this._locationList.value = data
    }

    fun setLoading(loading: Boolean) {
        this._loadingState.value = loading
    }

    fun setError(error: String) {
        this._errorState.value = error
    }
}