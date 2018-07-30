package com.mwhive.movies.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.mwhive.movies.di.Injector


abstract class BaseController : Controller(){

    private var injected = false

    override fun onContextAvailable(context: Context) {
        if(!injected) {
            Injector.inject(this)
            injected = true
        }
        super.onContextAvailable(context)
    }

}