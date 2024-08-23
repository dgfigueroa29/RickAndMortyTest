package com.boa.rickandmortytest.presentation.features.location

import androidx.paging.PagingData
import com.boa.rickandmortytest.domain.model.LocationModel
import kotlinx.coroutines.flow.MutableStateFlow

data class LocationState(
    val loadingState: MutableStateFlow<Boolean> = MutableStateFlow(false),
    val errorState: MutableStateFlow<String> = MutableStateFlow(""),
    val locationList: MutableStateFlow<PagingData<LocationModel>> = MutableStateFlow(PagingData.empty())
)