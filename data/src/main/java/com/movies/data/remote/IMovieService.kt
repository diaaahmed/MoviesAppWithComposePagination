package com.movies.data.remote

import com.movies.data.constant.Constant
import com.movies.domain.entity.CategoryResponse
import com.movies.domain.entity.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface IMovieService
{

    @GET("discover/movie?api_key=${Constant.API_KEY}")
    suspend fun getMovies(
        @Query ("page") page:Int,
        @Query ("language") language:String = "en",
        @Query ("with_genres") genresId:String
    ):MoviesResponse

    @GET("genre/movie/list?api_key=${Constant.API_KEY}")
    suspend fun getCategories(
        @Query ("language") language:String = "en"
    ):CategoryResponse
}