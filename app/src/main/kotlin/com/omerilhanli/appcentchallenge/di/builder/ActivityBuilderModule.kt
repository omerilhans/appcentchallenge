package com.omerilhanli.appcentchallenge.di.builder

import com.omerilhanli.appcentchallenge.di.module.ViewModelModule
import com.omerilhanli.appcentchallenge.ui.detail.DetailActivity
import com.omerilhanli.appcentchallenge.ui.detail.fragment.InfoPhotoFragment
import com.omerilhanli.appcentchallenge.ui.main.MainActivity
import com.omerilhanli.appcentchallenge.ui.main.fragment.RecentPhotosFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Tüm activity ve fragment'lerin injection'a katıldığı modüldür.
 * Her yeni activity ve fragment eklenmelidir.
 */
@Module
abstract class ActivityBuilderModule {

    // Main
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeRecentPhotosFragment(): RecentPhotosFragment

    // ------------------------------------------------

    // Detail
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeDetailActivity(): DetailActivity

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeInfoPhotoFragment(): InfoPhotoFragment
}