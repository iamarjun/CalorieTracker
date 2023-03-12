package com.arjun.core.domain.model

sealed class ActivityLevel(val name: String) {
    object Low : ActivityLevel("low")
    object Medium : ActivityLevel("medium")
    object High : ActivityLevel("high")

    companion object {
        fun fromString(name: String?) = when (name) {
            "low" -> Low
            "medium" -> Medium
            "high" -> High
            else -> throw IllegalArgumentException("$name is not a valid activity level type")
        }
    }
}
