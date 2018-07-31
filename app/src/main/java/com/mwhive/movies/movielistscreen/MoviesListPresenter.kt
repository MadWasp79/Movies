package com.mwhive.movies.movielistscreen

import com.mwhive.movies.data.MovieRequester
import com.mwhive.movies.di.ScreenScope
import javax.inject.Inject


/**
 * Created by MadWasp79 on 30-Jul-18.
 */

@ScreenScope
class MoviesListPresenter
@Inject constructor(val viewModel: MoviesListViewModel, val movieRequester: MovieRequester){
    init {
        loadMovies()
    }

    private fun loadMovies() {
        movieRequester.getPopularMoviseList()
                .doOnSubscribe { viewModel.loadingUpdated().accept(true) }
                .doOnEvent {d,t -> viewModel.loadingUpdated().accept(false) }
                .subscribe(viewModel.moviesUpdated(), viewModel.onError())
    }
}