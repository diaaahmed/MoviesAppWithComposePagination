package com.movies.domain.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState

class TestSource(): PagingSource<Int, Int>()
{

    init {
        println("Welcome new called")
    }
    override fun getRefreshKey(state: PagingState<Int, Int>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Int> {

        println("Welcome new called")

        return try{
            LoadResult.Page(
                data = testItem(),
                prevKey = 5,
                nextKey = 6
            )
        }
        catch (e:Exception)
        {
            LoadResult.Error(e)
        }

    }

    private fun testItem():List<Int>
    {
        return emptyList()
    }
}