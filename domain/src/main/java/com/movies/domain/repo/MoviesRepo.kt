package com.movies.domain.repo

import com.movies.domain.entity.MoviesResponse

interface MoviesRepo
{
    suspend fun getMovies(language:String,page:Int,genresId:String):MoviesResponse
}