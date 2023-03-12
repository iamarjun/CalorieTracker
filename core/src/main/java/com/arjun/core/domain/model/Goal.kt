package com.arjun.core.domain.model

sealed class Goal(val name: String) {
    object LoseWeight : Goal("lose")
    object MaintainWeight : Goal("maintain")
    object GainWeight : Goal("gain")

    companion object {
        fun fromString(name: String?) = when (name) {
            "lose" -> LoseWeight
            "maintain" -> MaintainWeight
            "gain" -> GainWeight
            else -> throw IllegalArgumentException("$name is not a valid goal type")
        }
    }
}
