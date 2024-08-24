package com.boa.rickandmortytest.data.mapper

import com.boa.rickandmortytest.data.network.LocationDto
import com.boa.rickandmortytest.domain.base.BaseMapper
import com.boa.rickandmortytest.domain.model.LocationModel

class LocationMapper : BaseMapper<LocationDto, LocationModel>() {
    override fun map(input: LocationDto): LocationModel = LocationModel(
        id = input.id ?: 0,
        name = input.name ?: "",
        type = input.type ?: "",
        dimension = input.dimension ?: "",
        residentUrls = input.residents ?: setOf(),
        url = input.url ?: "",
        created = input.created ?: ""
    )
}