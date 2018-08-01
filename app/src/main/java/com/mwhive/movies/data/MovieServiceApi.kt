package com.mwhive.movies.data

import com.mwhive.movies.model.Popular
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by MadWasp79 on 30-Jul-18.
 */

interface MovieServiceApi {

    //TODO remove api key before commit!
    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") api:String = "0d450e6d42b2241ce3e5cd5803687e2b") : Single<Popular>


}