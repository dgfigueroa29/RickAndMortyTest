package com.boa.rickandmortytest.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.boa.rickandmortytest.data.mapper.LocationMapper
import com.boa.rickandmortytest.domain.model.LocationModel

class LocationPagingSource(private val dataSource: LocationDataSource) :
    PagingSource<Int, LocationModel>() {
    override fun getRefreshKey(state: PagingState<Int, LocationModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationModel> {
        val pageNumber = params.key ?: 1
        return try {
            val response = dataSource.getAllLocations(pageNumber)
            //Convert DTOs to Model
            val list = if ((response.info?.count ?: 0) > 0) {
                LocationMapper().mapAll(response.results) // Directly assign the result
            } else {
                emptyList()
            }
            val prevKey = pageNumber.minus(1)
            val nextKey = pageNumber.plus(1)
            //When realm has support for kotlin 2.0 here we will call a local implementation to use cache

            LoadResult.Page(
                data = list,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}