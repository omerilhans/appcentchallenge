package com.omerilhanli.appcentchallenge.di.module

import com.omerilhanli.api_mdl.data.scheduler.AppScheduler
import com.omerilhanli.api_mdl.data.scheduler.AppSchedulerImpl
import dagger.Module
import dagger.Provides

@Module
object SystemModule {

    @JvmStatic
    @Provides
    internal fun provideAppScheduler(): AppScheduler {
        return AppSchedulerImpl()
    }
}