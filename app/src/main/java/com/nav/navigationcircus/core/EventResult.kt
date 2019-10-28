package com.nav.navigationcircus.core

class EventResult<T>(
    val result: T?,
    val error: EventError? = null) {
    init {
        check(!(result == null && error == null)) { "" }
    }

    val hasError = error != null

    val isSuccess: Boolean = !hasError
}