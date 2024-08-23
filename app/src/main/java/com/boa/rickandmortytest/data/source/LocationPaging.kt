package com.boa.rickandmortytest.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
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
            //val response = dataSource.getAllLocations(page)
            val list: List<LocationModel> = emptyList()
            val prev = page.minus(1)
            val next = page.plus(1)

            LoadResult.Page(
                data = list,
                prevKey = prev,
                nextKey = next
            )
        } catch (exception: Exception) {
            Timber.e("***Error Load Paging $page: ${exception.stackTraceToString()}")
            return LoadResult.Error(exception)
        }
    }
}