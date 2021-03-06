package com.omerilhanli.appcentchallenge.di.module

import com.omerilhanli.api_mdl.api.Api
import com.omerilhanli.api_mdl.data.repository.Repository
import com.omerilhanli.api_mdl.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Api isteklerinden ilgili datayı yönetip client'a sunan repository nesneleri sağlayan Modüldür.
 * Her yeni repository eklenmelidir.
 */
@Module
object RepositoryModule {

    @Provides
    @Singleton
    @JvmStatic
    internal fun provideRepository(api: Api): Repository {
        return RepositoryImpl(api)
    }
}