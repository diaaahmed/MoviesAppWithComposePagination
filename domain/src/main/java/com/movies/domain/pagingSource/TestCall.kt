package com.movies.domain.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.movies.domain.entity.MovieItem

class TestCall(word:String): PagingSource<Int, MovieItem>()
{
    init {
        println("Welcome to paging source")
    }

    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        return LoadResult.Error(Throwable(""))
    }
}