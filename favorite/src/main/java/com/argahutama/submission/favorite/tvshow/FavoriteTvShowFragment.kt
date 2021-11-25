package com.argahutama.submission.favorite.tvshow

import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.argahutama.submission.core.base.BaseFragment
import com.argahutama.submission.core.domain.model.Movie
import com.argahutama.submission.core.navigation.NavigationDirection
import com.argahutama.submission.core.ui.MovieAdapter
import com.argahutama.submission.favorite.FavoriteViewModel
import com.argahutama.submission.made.R
import com.argahutama.submission.made.databinding.FragmentTvShowBinding
import org.koin.android.viewmodel.ext.android.sharedViewModel

class FavoriteTvShowFragment : BaseFragment() {
    private val adapter by lazy { MovieAdapter() }
    override val viewModel by sharedViewModel<FavoriteViewModel>()
    override fun createBinding() = FragmentTvShowBinding.inflate(layoutInflater)

    private val moviesObserver = Observer<List<Movie>> { movies ->
        with(binding as FragmentTvShowBinding) {
            if (movies.isNullOrEmpty()) {
                progressBar.isVisible = false
                ivNotFound.isVisible = true
            } else {
                progressBar.isVisible = false
                ivNotFound.isVisible = false
            }
        }
        adapter.setData(movies)
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ) = makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ) = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val movie = adapter.getSwipedData(swipedPosition)
                val state = movie.favorite
                viewModel.setFavorite(movie, !state)
                showSnackbar(getString(R.string.set_unfavorite))
            }
        }
    })

    override fun setup() {
        viewModel.getFavoriteTvShows().observe(this, moviesObserver)
        super.setup()
    }

    override fun initView() = with(binding as FragmentTvShowBinding) {
        rvTvShows.adapter = adapter
    }

    override fun initAction() = with(binding as FragmentTvShowBinding) {
        adapter.onItemClick = { navigateTo(NavigationDirection.Detail(it)) }
        itemTouchHelper.attachToRecyclerView(rvTvShows)
    }
}