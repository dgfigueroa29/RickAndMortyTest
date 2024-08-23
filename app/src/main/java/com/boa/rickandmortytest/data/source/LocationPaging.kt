package com.boa.rickandmortytest.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.boa.rickandmortytest.data.mapper.LocationMapper
import com.boa.rickandmortytest.domain.model.LocationModel
import timber.log.Timber

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
            var prev: Int? = null
            var next: Int? = null

            if (response.results != null) {
                prev = page.minus(1)
                next = page.plus(1)
                list = LocationMapper().mapAll(response.results)
            }

            LoadResult.Page(
                data = list,
                prevKey = prev,
                nextKey = next
            )
        } catch (exception: Exception) {
            Timber.e("Error Load: ${exception.stackTraceToString()}")
            return LoadResult.Error(exception)
        }
    }
}