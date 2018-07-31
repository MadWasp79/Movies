package com.mwhive.movies.movielistscreen


import android.support.annotation.IdRes
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.mwhive.movies.R
import com.mwhive.movies.base.BaseController
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MoviesListController @Inject constructor(val presenter: MoviesListPresenter, private val viewModel: MoviesListViewModel): BaseController(){

    private lateinit var movieList: RecyclerView
    private lateinit var loadingView: View
    private lateinit var errorText: TextView

    override fun onViewBound(view : View){
        movieList = view.findViewById(R.id.movie_grid) as RecyclerView
        loadingView = view.findViewById(R.id.loading_indicator) as View
        errorText = view.findViewById(R.id.error_text) as TextView
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
                        },
                viewModel.movies()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(), //TODO
                viewModel.error()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe{ errorRes ->
                            if (errorRes == -1){
                                errorText.text = ""
                                errorText.visibility = View.GONE
                            } else {
                                errorText.visibility = View.VISIBLE
                                movieList.visibility = View.GONE
                                errorText.setText(errorRes)
                            }
                        })

    }


    override fun layoutRes(): Int = R.layout.screen_popular_movies
}

