package com.argahutama.submission.core.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.argahutama.submission.core.BuildConfig
import com.argahutama.submission.core.R
import com.argahutama.submission.core.databinding.ItemMovieBinding
import com.argahutama.submission.core.domain.model.Movie
import com.argahutama.submission.core.util.DifferenceUtil
import com.argahutama.submission.core.util.GlideListener
import com.bumptech.glide.Glide
import java.util.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        val diffUtilCallback = DifferenceUtil(listData, newListData)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        listData.clear()
        listData.addAll(newListData)
        diffResult.dispatchUpdatesTo(this)
    }

    fun getSwipedData(swipedPosition: Int) = listData[swipedPosition]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val bd = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(bd, parent.context)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount() = listData.size

    inner class MovieViewHolder(
        private val binding: ItemMovieBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) = with(binding) {
            ctvTitle.text = movie.title
            ctvDescription.text = movie.overview
            ctvRating.text = movie.voteAverage.toString()

            Glide.with(itemView.context)
                .load(context.getString(R.string.base_image_url, movie.posterPath))
                .listener(GlideListener(sivMovie, shimmer))
                .into(sivMovie)
        }

        init {
            binding.root.setOnClickListener { onItemClick?.invoke(listData[adapterPosition]) }
        }
    }
}