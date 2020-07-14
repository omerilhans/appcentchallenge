package com.omerilhanli.appcentchallenge.di.builder

import com.omerilhanli.appcentchallenge.di.module.ViewModelModule
import com.omerilhanli.appcentchallenge.ui.main.MainActivity
import com.omerilhanli.appcentchallenge.ui.main.fragment.RecentPhotosFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    // Main
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeRecentPhotosFragment(): RecentPhotosFragment

}