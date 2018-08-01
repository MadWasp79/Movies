package com.mwhive.movies.model

import com.squareup.moshi.Json

data class Popular(
        val page: Int,
        val total_results: Int,
        val total_pages: Int,
        @Json (name = "results") val results: List<Movie>
)

