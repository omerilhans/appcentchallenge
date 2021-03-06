package com.omerilhanli.appcentchallenge.di.component

import com.omerilhanli.appcentchallenge.App
import com.omerilhanli.appcentchallenge.di.module.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Tüm modüllerin kullanılarak injection için gerekli instance'ların generate edilmesini sağlayan
 * Component interface class'ı.
 * Dependency Injection başlama noktası da denebilir.
 */
@Singleton
@Component(
    modules = [
        ApplicationModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder
        fun build(): AppComponent
    }
}