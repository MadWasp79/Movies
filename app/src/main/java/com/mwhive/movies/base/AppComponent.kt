package com.mwhive.movies.base

import android.app.Application
import com.mwhive.movies.base.ActivityBindingModule
import com.mwhive.movies.data.MovieServiceModule
import com.mwhive.movies.networking.ServiceModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AppModule::class,
    ActivityBindingModule::class,
    ServiceModule::class,
    MovieServiceModule::class
])
interface AppComponent{
    fun inject(app: App)
}

