package com.boa.rickandmortytest.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.boa.rickandmortytest.data.mapper.LocationMapper
import com.boa.rickandmortytest.domain.model.LocationModel

class LocationPaging(private val dataSource: LocationDataSource) :
    PagingSource<Int, LocationModel>() {
    override fun getRefreshKey(state: PagingState<Int, LocationModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationModel> {
        val page = params.key ?: 1
        return try {
            val response = dataSource.getAllLocations(page)
            var list: List<LocationModel> = emptyList()
            val prev = page.minus(1)
            val next = page.plus(1)

            //Convert DTOs to Model
            //When realm has support for kotlin 2.0 here we will call a local implementation to use cache
            if ((response.info?.count ?: 0) > 0) {
                list = LocationMapper().mapAll(response.results)
            }

            LoadResult.Page(
                data = list,
                prevKey = prev,
                nextKey = next
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}