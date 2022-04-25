package com.alongo.ssetest.di.data.networking

import com.alongo.ssetest.data.networking.SseClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SseClientModule {
    @Provides
    fun provideSseClient() : SseClient = SseClient()
}
