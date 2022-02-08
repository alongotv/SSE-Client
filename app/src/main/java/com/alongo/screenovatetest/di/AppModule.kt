package com.alongo.screenovatetest.di

import com.alongo.screenovatetest.BuildConfig
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.sse.EventSource
import okhttp3.sse.EventSources

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().run {
        // Disabled event logging in production mode
        if (BuildConfig.DEBUG) {
            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        build()
    }

    @Provides
    fun provideSseFactory(httpClient: OkHttpClient): EventSource.Factory =
        EventSources.createFactory(httpClient)

    @Provides
    fun provideGson(): Gson {
        return Gson().newBuilder().setLenient().create()
    }
}
