/*
 * ServicesModule.kt
 * Created by Ulises Gonzalez on 22/04/24
 * Copyright (c) 2024. All rights reserved.
 */
package com.rm.mobile.di

import com.rm.mobile.data.network.service.CharacterServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ServicesModule {
    @Provides
    fun provideRecipesServices(retrofit: Retrofit): CharacterServices =
        retrofit.create(CharacterServices::class.java)
}
