package com.arjun.core.utils

sealed class UiEvents {
    data class Navigate(val route: String) : UiEvents()
    object NavigateUp : UiEvents()
}