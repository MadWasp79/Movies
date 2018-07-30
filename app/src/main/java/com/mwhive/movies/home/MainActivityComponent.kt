package com.mwhive.movies.home

import com.mwhive.movies.di.ActivityScope
import com.mwhive.movies.ui.DefaultScreenNavigator
import com.mwhive.movies.ui.NavigationModule
import dagger.Subcomponent
import dagger.android.AndroidInjector


@ActivityScope
@Subcomponent(modules = [MainScreenBindingModule::class, NavigationModule::class])
interface MainActivityComponent : AndroidInjector<MainActivity>{

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>(){
        override fun seedInstance(instance: MainActivity?) {}
    }
}