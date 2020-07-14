package com.omerilhanli.appcentchallenge.di.module

import android.app.Application
import android.content.Context
import com.omerilhanli.appcentchallenge.App
import com.omerilhanli.appcentchallenge.di.builder.ActivityBuilderModule
import dagger.Binds
import dagger.Module
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Module(
    includes = [
        AndroidSupportInjectionModule::class,
        SystemModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        ActivityBuilderModule::class
    ]
)
abstract class ApplicationModule {

    @Binds
    @Singleton
    abstract fun bindApplication(application: App): Application

    @Binds
    @Singleton
    abstract fun bindApplicationContext(application: Application): Context
}