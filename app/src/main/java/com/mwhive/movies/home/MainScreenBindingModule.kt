package com.mwhive.movies.home

import com.bluelinelabs.conductor.Controller
import com.mwhive.movies.di.ControllerKey
import com.mwhive.movies.movielistscreen.MoviesListComponent
import com.mwhive.movies.movielistscreen.MoviesListController
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = [MoviesListComponent::class])
abstract class MainScreenBindingModule{

    @Binds
    @IntoMap
    @ControllerKey(MoviesListController::class)
    abstract fun bindMoviesListInjector(builder: MoviesListComponent.Builder) : AndroidInjector.Factory<out Controller>
}

