package com.movieapp.hilt

import com.movieapp.commonUtils.Constant
import com.movieapp.data.remote.MovieAPI
import com.movieapp.data.repository.GetMovieDetailImpl
import com.movieapp.data.repository.GetMovieListImpl
import com.movieapp.domain.repository.MovieDetailRepository
import com.movieapp.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModules {

    @Provides
    @Singleton
    fun provideMovieAPI(): MovieAPI{

        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieAPI::class.java)
    }

    @Provides
    fun provideMovieRepository(movieAPI: MovieAPI) : MovieRepository{
        return GetMovieListImpl(movieAPI)
    }

    @Provides
    fun provideMovieListRepository(movieAPI: MovieAPI) : MovieDetailRepository {
        return GetMovieDetailImpl(movieAPI)
    }

}