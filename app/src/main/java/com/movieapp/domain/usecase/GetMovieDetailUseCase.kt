package com.movieapp.domain.usecase

import com.movieapp.commonUtils.ErrorMessage
import com.movieapp.commonUtils.Resource
import com.movieapp.data.model.toDomainMovieDetail
import com.movieapp.domain.model.MovieDetail
import com.movieapp.domain.repository.MovieDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private  val repository: MovieDetailRepository){

    operator fun invoke(id: Int): Flow<Resource<MovieDetail>> = flow{

        try{
            emit(Resource.Loading())

            val response = repository.getMovieDetails(id).toDomainMovieDetail()

            emit(Resource.Success(data = response))
        }
        catch(e:HttpException){
            emit(Resource.Error(message = e.localizedMessage?: ErrorMessage.ERROR_UNKNOWN.errorMessage))
        }
        catch(e: IOException){
            emit(Resource.Error(message = e.localizedMessage?: ErrorMessage.ERROR_INTERNET.errorMessage))
        }
        catch(e:Exception){
            emit(Resource.Error(message = e.localizedMessage?: ErrorMessage.ERROR_GENERIC.errorMessage))
        }
    }
}