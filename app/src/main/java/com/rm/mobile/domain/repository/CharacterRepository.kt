/*
 * ContainerRepository.kt
 * Created by Ulises Gonzalez on 02/05/24
 * Copyright (c) 2023. All rights reserved.
 */
package com.rm.mobile.domain.repository

import com.rm.mobile.data.network.utils.Resource
import com.rm.mobile.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacter(id: Int): Flow<Resource<Character>>
}
