package com.movies.domain.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.movies.domain.entity.MovieItem
import com.movies.domain.repo.MoviesRepo

class MoviePagingSource(
    private val moviesRepo: MoviesRepo,
    private val catId:String,
    val language:String,
) : PagingSource<Int, MovieItem>()
{
    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem>
    {
        println("diaa called")

        return try {
            val page = params.key ?: 1
            val response = moviesRepo.getMovies(language =language,page =page,genresId = catId)
            LoadResult.Page(
                data = response.movieItems,
                prevKey = if(page ==1)null else page.minus(1),
                nextKey = if(response.movieItems.isEmpty()) null else page.plus(1)
            )

        }
        catch (e:Exception)
        {
            println("diaa here called error ${e.message}")
            LoadResult.Error(e)
        }
    }
}