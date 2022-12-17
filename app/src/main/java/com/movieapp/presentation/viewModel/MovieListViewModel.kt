package com.movieapp.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movieapp.commonUtils.Resource
import com.movieapp.domain.usecase.GetMovieListUseCase
import com.movieapp.presentation.state.MovieListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val getMovieListUseCase: GetMovieListUseCase): ViewModel() {

    private val _movieList = MutableStateFlow<MovieListState>(MovieListState())
    val movieList : StateFlow<MovieListState> = _movieList

    init {
        movieList()
    }

    fun movieList()
    {
        getMovieListUseCase.invoke().onEach {
            when(it)
            {
                is Resource.Loading -> {
                    _movieList.value = MovieListState(isLoading = true)
                }
                is Resource.Error -> {
                    _movieList.value = MovieListState(error = it.message?:"")
                }
                is Resource.Success -> {
                    _movieList.value = MovieListState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }


}