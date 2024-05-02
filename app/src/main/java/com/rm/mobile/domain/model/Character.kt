package com.rm.mobile.domain.model

import com.rm.mobile.data.local.entity.CharacterEntity
import com.rm.mobile.data.network.response.CharacterResponse

data class Character(
    val id: Int,
    val name: String?,
    val status: String?,
    val specie: String?,
    val image: String?,
)

fun CharacterEntity.toDomain() =
    Character(id, name, status, specie, image)

fun CharacterResponse.toEntity() =
    CharacterEntity(id, name, status, specie, image)

fun CharacterResponse.toDomain() =
    Character(id, name, status, specie, image)
