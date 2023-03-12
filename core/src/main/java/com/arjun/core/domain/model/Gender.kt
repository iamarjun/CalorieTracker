package com.arjun.core.domain.model

sealed class Gender(val name: String) {
    object Male : Gender("male")
    object Female : Gender("female")

    companion object {
        fun fromString(name: String?) = when (name) {
            "male" -> Male
            "female" -> Female
            else -> throw IllegalArgumentException("$name is not a valid gender")
        }
    }
}
