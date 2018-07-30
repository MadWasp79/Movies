package com.mwhive.movies.di

import dagger.MapKey
import java.lang.annotation.ElementType
import kotlin.annotation.Target
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope
import com.bluelinelabs.conductor.Controller
import kotlin.reflect.KClass


@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope


@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ScreenScope


@MapKey
@Target(AnnotationTarget.FUNCTION)
annotation class ControllerKey(val value: KClass<out Controller>)

