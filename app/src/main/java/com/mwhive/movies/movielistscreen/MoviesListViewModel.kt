package com.mwhive.movies.movielistscreen

import com.jakewharton.rxrelay2.BehaviorRelay
import com.mwhive.movies.R

import com.mwhive.movies.di.ScreenScope
import com.mwhive.movies.model.Movie
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import javax.inject.Inject


/**
 * Created by MadWasp79 on 30-Jul-18.
 */
@ScreenScope
class MoviesListViewModel @Inject constructor(){
    private val moviesRelay : BehaviorRelay<List<Movie>> = BehaviorRelay.create()
    private val errorRelay : BehaviorRelay<Int> = BehaviorRelay.create()
    private val loadingRelay : BehaviorRelay<Boolean> = BehaviorRelay.create()
    private val reloadRelay : BehaviorRelay<Boolean> =  BehaviorRelay.create()

    fun loading(): Observable<Boolean> = loadingRelay
    fun movies(): Observable<List<Movie>> = moviesRelay
    fun error(): Observable<Int> = errorRelay
    fun reload(): Observable<Boolean> = reloadRelay

    fun loadingUpdated(): Consumer<Boolean> = loadingRelay
    fun moviesUpdated(): Consumer<List<Movie>> {
        errorRelay.accept(-1)
        return moviesRelay
    }

    fun onError(): Consumer<Throwable> {
        return Consumer(this::accept)
    }

    private fun accept(throwable: Throwable) {
        errorRelay.accept(R.string.api_error_movies)
    }

    private fun requestReload(): Consumer<Boolean> = reloadRelay



}