package com.mwhive.movies.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.Router
import com.mwhive.movies.R
import com.mwhive.movies.di.Injector
import com.mwhive.movies.di.ScreenInjector
import com.mwhive.movies.ui.ScreenNavigator
import java.util.*
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject lateinit var mScreenInjector: ScreenInjector
    @Inject lateinit var screenNavigator: ScreenNavigator

    lateinit var instanceId: String
    private lateinit var router: Router

    companion object {
        private const val INSTANCE_ID_KEY = "instance_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            instanceId = savedInstanceState.getString(INSTANCE_ID_KEY)
        } else {
            instanceId = UUID.randomUUID().toString()
        }
        Injector.inject(this)
        setContentView(layoutRes())

        val screenContainer: ViewGroup? = findViewById(R.id.screen_container)
        if (screenContainer == null) throw NullPointerException("Activity must have a view with id: screen_container")
        router = Conductor.attachRouter(this, screenContainer, savedInstanceState)
        screenNavigator.initWithRouter(router, initialScreen())
        monitorBackStack()
        super.onCreate(savedInstanceState)
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(INSTANCE_ID_KEY, instanceId)
    }

    override fun onBackPressed() {
        if(!screenNavigator.pop()) {super.onBackPressed()}
    }

    override fun onDestroy() {
        super.onDestroy()
        screenNavigator.clear()
        if (isFinishing) {
            Injector.clearComponent(this)
        }
    }

    @LayoutRes
    protected abstract fun layoutRes(): Int

    protected abstract fun initialScreen(): Controller

    fun getScreenInjectror(): ScreenInjector {
        return mScreenInjector
    }

    private fun monitorBackStack() {
        router.addChangeListener(object : ControllerChangeHandler.ControllerChangeListener {
            override fun onChangeStarted(
                    to: Controller?,
                    from: Controller?,
                    isPush: Boolean,
                    container: ViewGroup,
                    handler: ControllerChangeHandler) {}

            override fun onChangeCompleted(
                    to: Controller?,
                    from: Controller?,
                    isPush: Boolean,
                    container: ViewGroup,
                    handler: ControllerChangeHandler) {
                if (!isPush && from != null) Injector.clearComponent(from)
            }
        })
    }


}
