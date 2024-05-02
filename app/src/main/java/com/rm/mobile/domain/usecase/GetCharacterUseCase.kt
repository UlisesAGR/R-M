/*
 * GetCharacterUseCase.kt
 * Created by Ulises Gonzalez on 02/05/24
 * Copyright (c) 2023. All rights reserved.
 */
package com.rm.mobile.domain.usecase

import com.rm.mobile.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
) {
    suspend operator fun invoke(id: Int) = characterRepository.getCharacter(id)
}
