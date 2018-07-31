package com.mwhive.movies.networking

import dagger.Module
import dagger.Provides
import okhttp3.Call
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton


/**
 * Created by MadWasp79 on 30-Jul-18.
 */

@Module
object NetworkingModule {

    @Provides
    @Singleton
    fun provideOkHttp(): Call.Factory = OkHttpClient.Builder().build()


    @Provides
    @Named("base_url")
    fun provideBaseUrl(): String = "https://api.themoviedb.org/3"

}