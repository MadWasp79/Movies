package com.mwhive.movies.data

import com.mwhive.movies.model.Popular
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by MadWasp79 on 30-Jul-18.
 */

val baseImageUrl = "http://image.tmdb.org/t/p/w500/"

interface MovieServiceApi {

    //TODO remove api key before commit!
    @GET("/movie/popular")
    fun getPopularMovies(@Query("api_key") api:String = "ENTER YOU MOVIE DATA BASE API KEY HERE") : Single<Popular>


}