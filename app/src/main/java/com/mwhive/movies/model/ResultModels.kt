package com.mwhive.movies.model

import com.squareup.moshi.Json

data class Popular(
        val page: Int,
        val total_results: Int,
        val total_pages: Int,
        @Json (name = "results") val results: List<Movie>
)

data class Movie (
        @Json (name = "vote_count") val voteCount: Int,
        @Json (name = "id") val id: Int,
        @Json (name = "vote_average") val voteAverage: Double,
        @Json (name = "title") val title: String,
        @Json (name = "poster_path") val posterPath: String
)