package com.movieapp.presentation.fragment

import dagger.hilt.android.AndroidEntryPoint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.movieapp.databinding.FragmentMovieListBinding
import com.movieapp.databinding.ViewHolderMovieListBinding
import com.movieapp.domain.model.MovieList
import com.movieapp.presentation.viewModel.MovieListViewModel

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private var _binding : FragmentMovieListBinding? = null
        val binding: FragmentMovieListBinding
        get() = _binding!!

    private val movieListViewModel: MovieListViewModel by viewModels()

    private val movieListAdapter = MovieListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvMovieList.apply {
            adapter = movieListAdapter
        }

        lifecycle.coroutineScope.launchWhenCreated {
            movieListViewModel.movieList.collect() {
                if(it.isLoading) {
                    binding.showLoading.visibility = View.VISIBLE
                }
                if(it.error.isNotBlank()) {
                    binding.showLoading.visibility = View.GONE
                }
                it.data?.let {
                    binding.showLoading.visibility = View.GONE
                    movieListAdapter.setContentList(it.toMutableList())
                }
            }
        }
//region abc
//endregion
        movieListAdapter.itemClickListener {
            findNavController().navigate(
                MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(movieId = it.id!!)
            )
        }
    }
}

class MovieListAdapter: RecyclerView.Adapter<MovieListAdapter.MyViewHolder>() {

    private var listener :((MovieList)->Unit)?=null

    var list = mutableListOf<MovieList>()

    fun setContentList(list: MutableList<MovieList>) {
        this.list = list
        notifyDataSetChanged()
    }

    class MyViewHolder(val viewHolder: ViewHolderMovieListBinding) :
        RecyclerView.ViewHolder(viewHolder.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieListAdapter.MyViewHolder {
        val binding =
            ViewHolderMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    fun itemClickListener(l:(MovieList)->Unit){
        listener= l
    }

    override fun onBindViewHolder(holder: MovieListAdapter.MyViewHolder, position: Int) {
        holder.viewHolder.movie = this.list[position]

        holder.viewHolder.root.setOnClickListener {
            listener?.let {
                it(this.list[position])
            }
        }

    }

     override fun getItemCount(): Int {
        return this.list.size
    }
}