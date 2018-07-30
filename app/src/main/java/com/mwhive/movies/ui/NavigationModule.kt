package com.mwhive.movies.ui

import dagger.Binds
import dagger.Module
import dagger.Provides


/**
 * Created by MadWasp79 on 30-Jul-18.
 */
@Module
abstract class NavigationModule{

    @Binds
    abstract fun provideScreenNavigator(screenNavigator: DefaultScreenNavigator) : ScreenNavigator
}