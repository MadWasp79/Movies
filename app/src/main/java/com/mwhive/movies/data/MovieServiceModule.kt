package com.mwhive.movies.data

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton


/**
 * Created by MadWasp79 on 30-Jul-18.
 */

@Module
object MovieServiceModule {

    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit) : MovieServiceApi = retrofit.create(MovieServiceApi::class.java)
}