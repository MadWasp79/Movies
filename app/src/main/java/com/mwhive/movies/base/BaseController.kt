package com.mwhive.movies.base

import android.content.Context
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.mwhive.movies.di.Injector
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BaseController : Controller(){

    private val disposables: CompositeDisposable = CompositeDisposable()

    private var injected = false

    override fun onContextAvailable(context: Context) {
        if(!injected) {
            Injector.inject(this)
            injected = true
        }
        super.onContextAvailable(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(layoutRes(), container, false)
        onViewBound(view)
        disposables.addAll(*subscriptions())
        return view
    }

    override fun onDestroy() {
        disposables.clear()
    }

    protected open fun onViewBound(view: View) {}

    protected open fun subscriptions(): Array<Disposable?> = arrayOfNulls(0)

    @LayoutRes abstract fun layoutRes(): Int


}