package com.mwhive.movies.base

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule (private val application: Application){

    @Provides
    fun provideAppContext(): Context = application

}