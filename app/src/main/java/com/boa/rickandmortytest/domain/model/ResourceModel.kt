package com.boa.rickandmortytest.domain.model

sealed class ResourceModel<T>(
    val data: T? = null,
    val message: String = "",
    val flag: Boolean = false
) {
    class Success<T>(data: T) : ResourceModel<T>(data)
    class Loading<T>(flag: Boolean) : ResourceModel<T>(flag = flag)
    class Error<T>(message: String) : ResourceModel<T>(message = message)
}