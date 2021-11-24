package com.argahutama.submission.made.movie

import androidx.lifecycle.Observer
import com.argahutama.submission.core.base.BaseFragment
import com.argahutama.submission.core.data.Resource
import com.argahutama.submission.core.domain.model.Movie
import com.argahutama.submission.core.navigation.NavigationDirection
import com.argahutama.submission.core.ui.MovieAdapter
import com.argahutama.submission.custom_ui.CustomSnack
import com.argahutama.submission.made.databinding.FragmentMovieBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : BaseFragment() {
    private val adapter by lazy { MovieAdapter() }
    override val viewModel by viewModel<MovieViewModel>()
    override fun createBinding() = FragmentMovieBinding.inflate(layoutInflater)

    private val moviesObserver = Observer<Resource<List<Movie>>> {
        if (it != null) with(binding) {
            when (it) {
                is Resource.Loading -> {
//                    binding.progressBar.visibility = View.VISIBLE
//                    binding.notFound.visibility = View.GONE
//                    binding.notFoundText.visibility = View.GONE
                }
                is Resource.Success -> {
//                    binding.progressBar.visibility = View.GONE
//                    binding.notFound.visibility = View.GONE
//                    binding.notFoundText.visibility = View.GONE
                    adapter.setData(it.data)
                }
                is Resource.Error -> {
//                    binding.progressBar.visibility = View.GONE
//                    binding.notFound.visibility = View.VISIBLE
//                    binding.notFoundText.visibility = View.VISIBLE
                    showSnackbar(it.message.orEmpty(), CustomSnack.FAILED)
                }
            }
        }
    }

    override fun setup() {
        super.setup()
        viewModel.getMovies().observe(viewLifecycleOwner, moviesObserver)
    }

    override fun initView() = with(binding as FragmentMovieBinding) {
        rvMovies.adapter = adapter
    }

    override fun initAction() {
        adapter.onItemClick = { navigateTo(NavigationDirection.Detail(it)) }
    }
}