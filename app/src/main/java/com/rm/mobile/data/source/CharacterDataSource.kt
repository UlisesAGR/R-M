/*
 * CharacterDataSource.kt
 * Created by Ulises Gonzalez on 02/05/24
 * Copyright (c) 2023. All rights reserved.
 */
package com.rm.mobile.data.source

import com.rm.mobile.data.network.service.CharacterServices
import com.rm.mobile.data.network.utils.toResult
import com.rm.mobile.domain.model.toDomain
import javax.inject.Inject

class CharacterDataSource @Inject constructor(
    private val characterServices: CharacterServices,
) {
    suspend fun getCharacter(id: Int) =
        characterServices.getCharacter(id).toResult { toDomain() }
}
