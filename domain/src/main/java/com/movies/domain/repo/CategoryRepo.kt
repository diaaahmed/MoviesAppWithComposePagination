package com.movies.domain.repo

import com.movies.domain.entity.CategoryResponse

interface CategoryRepo
{
    suspend fun getCategories(language:String):CategoryResponse
}