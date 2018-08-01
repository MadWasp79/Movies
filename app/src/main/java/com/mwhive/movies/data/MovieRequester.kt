package com.mwhive.movies.data

import com.mwhive.movies.model.Movie
import com.mwhive.movies.model.Popular
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Created by MadWasp79 on 30-Jul-18.
 */

class MovieRequester @Inject
constructor(private val service: MovieServiceApi) {
    lateinit var test: Popular
    fun getPopularMoviesList() : Single<List<Movie>> = service
            .getPopularMovies()
            .map { it.results }
            .subscribeOn(Schedulers.io())
}