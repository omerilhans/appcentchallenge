package com.omerilhanli.appcentchallenge.di.module

import androidx.lifecycle.ViewModel
import com.omerilhanli.appcentchallenge.di.factory.ViewModelKey
import com.omerilhanli.appcentchallenge.ui.detail.DetailViewModel
import com.omerilhanli.appcentchallenge.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun mainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun detailViewModel(viewModel: DetailViewModel): ViewModel
}
