package com.movies.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.movies.domain.entity.MovieItem
import com.movies.domain.pagingSource.MoviePagingSource
import com.movies.domain.repo.MoviesRepo
import com.movies.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesUseCase @Inject constructor(private val repository: MoviesRepo) {

    operator fun invoke(lang: String, catId: String): Flow<Resource<Pager<Int, MovieItem>>> = flow {
        try {
            emit(Resource.Loading())

            val getMovies = Pager(
                config = PagingConfig(pageSize = 10),
            ) {
                MoviePagingSource(repository, catId, lang)
            }
            emit(Resource.Success(getMovies))
        } catch (e: Exception) {
            emit(Resource.Error("${e.localizedMessage} : An unexpected error happened"))

        }
    }
}

