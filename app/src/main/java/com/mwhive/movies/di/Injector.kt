package com.mwhive.movies.di

import android.app.Activity
import com.bluelinelabs.conductor.Controller

object Injector {


    //inject Activity
    @JvmStatic
    fun inject(activity: Activity) {
       ActivityInjector.get(activity).inject(activity)
    }

    //clear Activity after it finished
    @JvmStatic
    fun clearComponent(activity: Activity) {
        ActivityInjector.get(activity).clear(activity)
    }

    //inject Controller
    @JvmStatic
    fun inject(controller: Controller) {
        ScreenInjector.get(controller.activity!!).inject(controller)
    }

    //clear Controller
    @JvmStatic
    fun clearComponent(controller: Controller) {
        ScreenInjector.get(controller.activity!!).clear(controller)
    }

}