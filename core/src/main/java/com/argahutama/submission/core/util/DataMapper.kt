package com.argahutama.submission.core.util

import com.argahutama.submission.core.data.source.local.entity.MovieEntity
import com.argahutama.submission.core.data.source.remote.response.MovieResponse
import com.argahutama.submission.core.data.source.remote.response.TvShowResponse
import com.argahutama.submission.core.domain.model.Movie

object DataMapper {
    fun mapMovieResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                it.id ?: 0,
                it.overview.orEmpty(),
                it.originalLanguage.orEmpty(),
                it.releaseDate.orEmpty(),
                it.popularity ?: .0,
                it.voteAverage ?: .0,
                it.title.orEmpty(),
                it.voteCount ?: 0,
                it.posterPath.orEmpty(),
                favorite = false,
                isTvShows = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapTvShowResponsesToEntities(input: List<TvShowResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                it.id ?: 0,
                it.overview.orEmpty(),
                it.originalLanguage.orEmpty(),
                it.firstAirDate.orEmpty(),
                it.popularity ?: .0,
                it.voteAverage  ?: .0,
                it.name.orEmpty(),
                it.voteCount ?: 0,
                it.posterPath.orEmpty(),
                favorite = false,
                isTvShows = true
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> = input.map {
        Movie(
            it.id,
            it.overview,
            it.originalLanguage,
            it.releaseDate,
            it.popularity,
            it.voteAverage,
            it.title,
            it.voteCount,
            it.posterPath,
            favorite = it.favorite,
            isTvShows = it.isTvShows
        )
    }

    fun mapDomainToEntity(input: Movie): MovieEntity = MovieEntity(
        input.id,
        input.overview,
        input.originalLanguage,
        input.releaseDate,
        input.popularity,
        input.voteAverage,
        input.title,
        input.voteCount,
        input.posterPath,
        favorite = input.favorite,
        isTvShows = input.isTvShows
    )
}