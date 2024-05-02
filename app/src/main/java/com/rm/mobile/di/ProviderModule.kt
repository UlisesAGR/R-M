/*
 * RepositoryModule.kt
 * Created by Ulises Gonzalez on 02/05/24
 * Copyright (c) 2023. All rights reserved.
 */
package com.rm.mobile.di

import com.rm.mobile.data.provider.ResourceProviderImpl
import com.rm.mobile.domain.provider.ResourceProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ProviderModule {
    @Singleton
    @Binds
    abstract fun provideResourceProvider(containerResourceProviderImpl: ResourceProviderImpl): ResourceProvider
}
