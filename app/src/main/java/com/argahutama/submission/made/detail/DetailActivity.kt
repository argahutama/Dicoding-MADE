package com.argahutama.submission.made.detail

import androidx.core.content.ContextCompat
import com.argahutama.submission.core.base.BaseActivity
import com.argahutama.submission.core.domain.model.Movie
import com.argahutama.submission.core.navigation.Extra
import com.argahutama.submission.made.R
import com.argahutama.submission.made.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : BaseActivity() {
    private var movie: Movie? = null
    override val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    override val viewModel by viewModel<DetailViewModel>()

    override fun setup() {
        loadArgs()
        super.setup()
    }

    override fun initView() = with(binding) {
        ctvTitleDetail.text = movie?.title.orEmpty()
        ctvDate.text = movie?.releaseDate
        ctvOverview.text = movie?.overview
        ctvPopularity.text = getString(
            R.string.popularity_detail,
            movie?.popularity.toString(),
            movie?.voteCount.toString(),
            movie?.voteAverage.toString()
        )
        ctvRating.text = movie?.voteAverage.toString()
        Glide.with(this@DetailActivity)
            .load(getString(R.string.base_image_url, movie?.posterPath))
            .into(ivPosterTopBar)
        ivPosterTopBar.tag = movie?.posterPath.orEmpty()

        Glide.with(this@DetailActivity)
            .load(getString(R.string.base_image_url, movie?.posterPath))
            .into(sivSubPoster)
        sivSubPoster.tag = movie?.posterPath
    }

    override fun initAction() = with(binding) {
        var favoriteState = movie?.favorite ?: false
        setFavorite(favoriteState)
        favoriteButton.setOnClickListener {
            favoriteState = !favoriteState
            movie?.let { movie -> viewModel.setFavoriteMovie(movie, favoriteState) }
            setFavorite(favoriteState)
        }
        ivBackButton.setOnClickListener { onBackPressed() }
    }

    private fun loadArgs() {
        movie = intent.getParcelableExtra(Extra.MOVIE)
    }

    private fun ActivityDetailBinding.setFavorite(state: Boolean) =
        if (state)
            favoriteButton.setImageDrawable(
                ContextCompat.getDrawable(this@DetailActivity, R.drawable.ic_favorite_selected)
            )
        else favoriteButton.setImageDrawable(
            ContextCompat.getDrawable(this@DetailActivity, R.drawable.ic_favorite_unselected)
        )

}