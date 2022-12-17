package com.movieapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.movieapp.commonUtils.ConnectivityObserver
import com.movieapp.commonUtils.NetworkConnectivityObserver
import com.movieapp.databinding.FragmentMovieDetailBinding
import com.movieapp.presentation.viewModel.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private var _binding:FragmentMovieDetailBinding? = null
    val binding:FragmentMovieDetailBinding
    get() = _binding!!

    private val args: MovieDetailFragmentArgs by navArgs()

    private val viewModel: MovieDetailViewModel by viewModels()

    private lateinit var connectivityObserver : ConnectivityObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        connectivityObserver = NetworkConnectivityObserver(requireContext())
        connectivityObserver.observe().onEach {
            if(!it.name.matches(Regex("Available")))
            {
                println("Network Status is $it")
            }
        }.launchIn(lifecycleScope)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieDetailBinding.inflate(inflater,container,false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.movieId?.let {
            viewModel.getMovieDetail(it)
        }

        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.movieDetail.collect{
                if(it.isLoading)
                {
                    binding.showLoading.visibility = View.VISIBLE
                    binding.dataFetch.visibility = View.GONE
                }
                if(it.error.isNotBlank())
                {
                    binding.showLoading.visibility = View.GONE
                    binding.dataFetch.visibility = View.VISIBLE
                }
                binding.showLoading.visibility = View.GONE
                binding.dataFetch.visibility = View.VISIBLE
                it.data?.let { binding.movieDetail= it }
            }
        }

        binding.detailsBackArrow.setOnClickListener{
            findNavController().popBackStack()
        }

    }
}