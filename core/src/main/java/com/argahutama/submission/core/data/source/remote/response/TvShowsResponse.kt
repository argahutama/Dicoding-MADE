package com.argahutama.submission.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowsResponse(@SerializedName("results") val results: List<TvShowResponse>)