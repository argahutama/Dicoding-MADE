package com.argahutama.submission.core.util

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtil {
    fun getSortedQueryMovies(): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movieEntities where isTvShow = 0 ")
        simpleQuery.append("ORDER BY releaseDate DESC")
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getSortedQueryTvShows(): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movieEntities where isTvShow = 1 ")
        simpleQuery.append("ORDER BY releaseDate DESC")
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getSortedQueryFavoriteMovies(): SimpleSQLiteQuery {
        val simpleQuery =
            StringBuilder().append("SELECT * FROM movieEntities where favorite = 1 and isTvShow = 0 ")
        simpleQuery.append("ORDER BY releaseDate DESC")
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getSortedQueryFavoriteTvShows(): SimpleSQLiteQuery {
        val simpleQuery =
            StringBuilder().append("SELECT * FROM movieEntities where Favorite = 1 and isTvShow = 1 ")
        simpleQuery.append("ORDER BY releaseDate DESC")
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}