package com.mwhive.movies.model

import com.squareup.moshi.Json


/**
 * Created by MadWasp79 on 01-Aug-18.
 */

data class Movie (
        @Json(name = "vote_count") val voteCount: Int,
        @Json(name = "id") val id: Int,
        @Json(name = "vote_average") val voteAverage: Double,
        @Json(name = "title") val title: String,
        @Json(name = "poster_path") val posterPath: String
)