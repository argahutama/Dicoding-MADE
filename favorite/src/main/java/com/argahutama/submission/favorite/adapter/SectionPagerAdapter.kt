package com.argahutama.submission.favorite.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.argahutama.submission.favorite.R
import com.argahutama.submission.favorite.movie.FavoriteMovieFragment
import com.argahutama.submission.favorite.tvshow.FavoriteTvShowFragment

class SectionPagerAdapter(
    private val mContext: Context,
    fm: FragmentManager
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    @StringRes
    private val tabTitles = intArrayOf(R.string.movies, R.string.tv_shows)
    private val fragment: List<Fragment> = listOf(FavoriteMovieFragment(), FavoriteTvShowFragment())
    override fun getPageTitle(position: Int) = mContext.getString(tabTitles[position])
    override fun getCount() = tabTitles.size
    override fun getItem(position: Int) = fragment[position]
}