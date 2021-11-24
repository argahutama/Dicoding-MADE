package com.argahutama.submission.core.util

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.argahutama.submission.core.domain.model.Movie

class DifferenceUtil(private val oldList: List<Movie>, private val newList: List<Movie>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val (_,
            overview,
            originalLanguage,
            releaseDate,
            popularity,
            voteAverage,
            title,
            voteCount,
            posterPath,
            favorite,
            isTvShows) = oldList[oldPosition]
        val (_,overview1,
            originalLanguage1,
            releaseDate1,
            popularity1,
            voteAverage1,
            title1,
            voteCount1,
            posterPath1,
            favorite1,
            isTvShows1) = newList[newPosition]

        return overview == overview1
                && originalLanguage == originalLanguage1
                && releaseDate == releaseDate1
                && popularity == popularity1
                && voteAverage == voteAverage1
                && title == title1
                && voteCount == voteCount1
                && posterPath == posterPath1
                && favorite == favorite1
                && isTvShows == isTvShows1
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? =
        super.getChangePayload(oldPosition, newPosition)
}