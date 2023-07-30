package com.movies.data.repo

import com.movies.data.remote.IMovieService
import com.movies.domain.entity.CategoryResponse
import com.movies.domain.repo.CategoryRepo
import javax.inject.Inject

class CategoryRepoImpl @Inject constructor(private val moviesService: IMovieService): CategoryRepo
{
    override suspend fun getCategories(language: String): CategoryResponse  = moviesService.getCategories(language)
}