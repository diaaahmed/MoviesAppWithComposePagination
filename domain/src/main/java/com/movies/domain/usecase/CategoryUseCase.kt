package com.movies.domain.usecase

import com.movies.domain.entity.CategoryResponse
import com.movies.domain.repo.CategoryRepo
import com.movies.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryUseCase @Inject constructor(private val repository: CategoryRepo) {

    operator fun invoke(lang: String):Flow<Resource<CategoryResponse>> = flow {
        try {
            emit(Resource.Loading())
            val cats = repository.getCategories(lang)
            emit(Resource.Success(cats))
        }catch (e:Exception){
            emit(Resource.Error("${e.localizedMessage} : An unexpected error happened"))
        }
    }
}

