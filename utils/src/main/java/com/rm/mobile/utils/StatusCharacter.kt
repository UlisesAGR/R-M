package com.rm.mobile.utils

fun String?.toStatus(): StatusCharacter =
    when (this?.lowercase() ?: "unknown") {
        "alive" -> StatusCharacter.ALIVE
        "dead" -> StatusCharacter.DEAD
        else -> StatusCharacter.UNKNOWN
    }

enum class StatusCharacter {
    ALIVE,
    DEAD,
    UNKNOWN,
}
