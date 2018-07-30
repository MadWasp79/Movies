package com.mwhive.movies.base

import android.app.Application
import com.mwhive.movies.base.ActivityBindingModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, ActivityBindingModule::class])
interface AppComponent{
    fun inject(app: App)
}

