package com.mwhive.movies.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bluelinelabs.conductor.Controller
import com.mwhive.movies.R
import com.mwhive.movies.base.BaseActivity
import com.mwhive.movies.movielistscreen.MoviesListController

class MainActivity : BaseActivity() {


    override fun layoutRes(): Int {
        return R.layout.activity_main
    }

    override fun initialScreen(): Controller = MoviesListController()

}
