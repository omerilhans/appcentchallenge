package com.omerilhanli.appcentchallenge.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.omerilhanli.api_mdl.api.Api
import com.omerilhanli.appcentchallenge.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkModule {

    @Singleton
    @Provides
    @JvmStatic
    internal fun provideUrl(): String {
        return BuildConfig.BASE_URL
    }

    @Singleton
    @Provides
    @JvmStatic
    internal fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Singleton
    @Provides
    @JvmStatic
    internal fun provideClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        builder.addNetworkInterceptor(loggingInterceptor)
        return builder.build()
    }

    @Singleton
    @Provides
    @JvmStatic
    internal fun provideRetrofit(baseUrl: String, gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    @JvmStatic
    internal fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    private const val REQUEST_TIMEOUT = 60
    private const val CONNECT_TIMEOUT = 30
}