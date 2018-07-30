package com.mwhive.movies.base

import android.app.Application
import com.mwhive.movies.di.ActivityInjector
import javax.inject.Inject


class App : Application() {

    @Inject lateinit var activityInjector : ActivityInjector

    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()

        component.inject(this)
    }

//    fun getActivityInjector(): ActivityInjector {
//        return mActivityInjector
//    }


}