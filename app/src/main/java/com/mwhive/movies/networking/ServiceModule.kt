package com.mwhive.movies.networking

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


/**
 * Created by MadWasp79 on 30-Jul-18.
 */

@Module(includes = [NetworkingModule::class])
object ServiceModule{

    @Provides
    @Singleton
    fun provideMoshi()= Moshi.Builder().build() //no custom adapter factories here, but we leave it here for any changes in the future


    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi, callFactory: Call.Factory, baseUrl: String) =
            Retrofit.Builder()
                    .callFactory(callFactory)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(baseUrl)
                    .build()

}
