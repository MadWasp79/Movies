package com.mwhive.movies.movielistscreen

import com.mwhive.movies.di.ScreenScope
import dagger.Subcomponent
import dagger.android.AndroidInjector


/**
 * Created by MadWasp79 on 29-Jul-18.
 */

@ScreenScope
@Subcomponent
interface MoviesListComponent : AndroidInjector<MoviesListController>{

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MoviesListController>()

}