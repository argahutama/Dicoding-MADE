package com.argahutama.submission.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MoviesResponse(@SerializedName("results") val results: List<MovieResponse>)