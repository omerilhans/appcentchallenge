package com.omerilhanli.appcentchallenge

import com.omerilhanli.appcentchallenge.di.component.DaggerAppComponent
import dagger.android.DaggerApplication

class App : DaggerApplication() {

    private var applicationInjector = DaggerAppComponent.builder()
        .application(this)
        .build()

    override fun applicationInjector() = applicationInjector
}