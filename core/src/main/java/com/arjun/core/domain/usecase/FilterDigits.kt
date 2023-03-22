package com.arjun.core.domain.usecase

import javax.inject.Inject

class FilterDigits @Inject constructor() {
    operator fun invoke(text: String): Int {
        return text.filter { it.isDigit() }.toInt()
    }
}