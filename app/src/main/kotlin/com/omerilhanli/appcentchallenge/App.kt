package com.omerilhanli.appcentchallenge

import com.omerilhanli.appcentchallenge.di.component.AppComponent
import com.omerilhanli.appcentchallenge.di.component.DaggerAppComponent
import dagger.android.DaggerApplication

class App : DaggerApplication() {

    private lateinit var applicationInjector: AppComponent

    override fun applicationInjector() = applicationInjector

    override fun onCreate() {
        super.onCreate()
        applicationInjector = DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}