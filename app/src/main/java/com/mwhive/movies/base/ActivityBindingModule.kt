package com.mwhive.movies.base

import android.app.Activity
import com.mwhive.movies.home.MainActivity
import com.mwhive.movies.home.MainActivityComponent
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap


@Module(subcomponents = arrayOf(MainActivityComponent::class))
abstract class ActivityBindingModule{

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    abstract fun provideMainActivityInjector(builder: MainActivityComponent.Builder) : AndroidInjector.Factory<out Activity>

}