package com.movieapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movieapp.commonUtils.Resource
import com.movieapp.domain.usecase.GetMovieDetailUseCase
import com.movieapp.presentation.state.MovieDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val getMovieDetailUseCase: GetMovieDetailUseCase): ViewModel() {

    private val _movieDetail = MutableStateFlow<MovieDetailState>(MovieDetailState())
    val movieDetail: StateFlow<MovieDetailState> = _movieDetail

    fun getMovieDetail(id: Int)
    {
        getMovieDetailUseCase(id).onEach {
            when(it)
            {
                is Resource.Loading ->
                {
                    _movieDetail.value = MovieDetailState(isLoading = true)
                }
                is Resource.Error ->
                {
                    _movieDetail.value = MovieDetailState(error = it.message?:"")
                }
                is Resource.Success ->
                {
                    _movieDetail.value = MovieDetailState(data = it.data)
                }

            }

        }.launchIn(viewModelScope)
    }

}