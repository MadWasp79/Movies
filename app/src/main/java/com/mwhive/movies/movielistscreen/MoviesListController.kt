package com.mwhive.movies.movielistscreen


import android.content.res.Configuration
import android.media.VolumeShaper
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.jakewharton.rxbinding2.view.RxView
import com.mwhive.movies.R
import com.mwhive.movies.base.BaseController
import com.mwhive.movies.customviews.WrappableGridLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

import javax.inject.Inject

class MoviesListController : BaseController(){

    @Inject lateinit var viewModel: MoviesListViewModel
    @Inject lateinit var presenter: MoviesListPresenter

    private lateinit var movieList: RecyclerView
    private lateinit var loadingView: View
    private lateinit var errorText: TextView
    private lateinit var reloadFAB: FloatingActionButton



    override fun onViewBound(view : View){
        movieList = view.findViewById(R.id.movie_grid) as RecyclerView
        loadingView = view.findViewById(R.id.loading_indicator) as View
        errorText = view.findViewById(R.id.error_text) as TextView
        reloadFAB = view.findViewById(R.id.reload_fab) as FloatingActionButton
        val columnNum =  if (this.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 4
        movieList.layoutManager = GridLayoutManager(view.context, columnNum)
        movieList.adapter = MovieAdapter()
        super.onViewBound(view)
    }

    override fun subscriptions(): Array<Disposable?>{
        return arrayOf(
                viewModel.loading()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { loading ->
                            loadingView.visibility = if (loading) View.VISIBLE else View.GONE
                            movieList.visibility = if (loading) View.GONE else View.VISIBLE
                            errorText.visibility = if (loading) View.GONE else errorText.visibility
                            reloadFAB.visibility = if (loading) View.GONE else reloadFAB.visibility
                        },
                viewModel.movies()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((movieList.adapter as MovieAdapter)::setData),
                viewModel.error()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe{ errorRes ->
                            if (errorRes == -1){
                                errorText.text = ""
                                errorText.visibility = View.GONE
                                reloadFAB.visibility = View.GONE
                            } else {
                                reloadFAB.visibility = View.VISIBLE
                                errorText.visibility = View.VISIBLE
                                movieList.visibility = View.GONE
                                errorText.setText(errorRes)
                            }
                        },
                RxView.clicks(reloadFAB).subscribe{ presenter.reload() })
    }

    override fun layoutRes(): Int = R.layout.screen_popular_movies
}

