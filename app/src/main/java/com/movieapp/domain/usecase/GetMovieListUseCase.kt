package com.movieapp.domain.usecase

import com.movieapp.commonUtils.ErrorMessage
import com.movieapp.commonUtils.Resource
import com.movieapp.data.model.toDomainMovieList
import com.movieapp.domain.model.MovieList
import com.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(private val repository: MovieRepository){

    operator fun invoke(): Flow<Resource<List<MovieList>>> = flow{

        try{
            emit(Resource.Loading())

            val response = repository.getMovieList()
            val listMovie =
                if(response.results.isNullOrEmpty())
                    emptyList()
                else
                    response.results.map { it.toDomainMovieList() }

            emit(Resource.Success(data = listMovie))
        }
        catch(e:HttpException){
            emit(Resource.Error(message = e.localizedMessage?: ErrorMessage.ERROR_UNKNOWN.errorMessage))
        }
        catch(e: IOException){
            emit(Resource.Error(message = e.localizedMessage?:ErrorMessage.ERROR_INTERNET.errorMessage))
        }
        catch(e:Exception){
            emit(Resource.Error(message = e.localizedMessage?:ErrorMessage.ERROR_GENERIC.errorMessage))
        }
    }
}