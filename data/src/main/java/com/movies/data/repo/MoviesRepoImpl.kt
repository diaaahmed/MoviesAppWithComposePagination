package com.movies.data.repo


import android.util.Log
import com.movies.data.remote.IMovieService
import com.movies.domain.entity.MoviesResponse
import com.movies.domain.repo.MoviesRepo
import javax.inject.Inject

class MoviesRepoImpl @Inject constructor(private val moviesService: IMovieService): MoviesRepo
{
    override suspend fun getMovies(language: String, page: Int, genresId: String): MoviesResponse{
        Log.d("TAG", "getMovies: here")
        return moviesService.getMovies(language = language,page= page, genresId = genresId)
    }
}