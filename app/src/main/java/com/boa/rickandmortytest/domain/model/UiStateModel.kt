package com.boa.rickandmortytest.domain.model

sealed class UiStateModel<T>(
    val data: T? = null,
    val message: String = "",
    val flag: Boolean = false
) {
    class Success<T>(data: T) : UiStateModel<T>(data)
    class Loading<T>(flag: Boolean) : UiStateModel<T>(flag = flag)
    class Error<T>(message: String) : UiStateModel<T>(message = message)
}