package com.argahutama.submission.made.tvshow

import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.argahutama.submission.core.base.BaseFragment
import com.argahutama.submission.core.data.Resource
import com.argahutama.submission.core.domain.model.Movie
import com.argahutama.submission.core.navigation.NavigationDirection
import com.argahutama.submission.core.ui.MovieAdapter
import com.argahutama.submission.custom_ui.CustomSnack
import com.argahutama.submission.made.databinding.FragmentTvShowBinding
import com.argahutama.submission.made.main.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
@FlowPreview
class TvShowFragment: BaseFragment() {
    private val adapter by lazy { MovieAdapter() }
    override val viewModel by viewModel<TvShowViewModel>()
    private val mainViewModel: MainViewModel by viewModel()
    override fun createBinding() = FragmentTvShowBinding.inflate(layoutInflater)

    private val tvShowsObserver = Observer<Resource<List<Movie>>> {
        if (it != null) with(binding as FragmentTvShowBinding) {
            when (it) {
                is Resource.Loading -> {
                    progressBar.isVisible = true
                    ivNotFound.isVisible = false
                }
                is Resource.Success -> {
                    progressBar.isVisible = false
                    ivNotFound.isVisible = false
                    adapter.setData(it.data)
                }
                is Resource.Error -> {
                    progressBar.isVisible = false
                    ivNotFound.isVisible = true
                    showSnackbar(it.message.orEmpty(), CustomSnack.FAILED)
                }
            }
        }
    }

    override fun setup() {
        super.setup()
        viewModel.getTvShows().observe(viewLifecycleOwner, tvShowsObserver)
        mainViewModel.tvShowResult.observe(viewLifecycleOwner, { tvShows ->
            with(binding as FragmentTvShowBinding) {
                if (tvShows.isNullOrEmpty()) {
                    progressBar.isVisible = false
                    ivNotFound.isVisible = true
                } else {
                    progressBar.isVisible = false
                    ivNotFound.isVisible = false
                }
            }
            adapter.setData(tvShows)
        })
    }

    override fun initView() = with(binding as FragmentTvShowBinding) {
        rvTvShows.adapter = adapter
    }

    override fun initAction() = with(binding as FragmentTvShowBinding) {
        adapter.onItemClick = { navigateTo(NavigationDirection.Detail(it)) }
        ctfSearch.doOnTextChanged { text, _, _, _ ->
            if (text?.isEmpty() == true) reset()
            else debounce { mainViewModel.setSearchQuery(text.toString()) }
        }
    }

    private fun reset() = viewModel.getTvShows().observe(viewLifecycleOwner, tvShowsObserver)
}