package com.lelloman.instrumentedtestutils

import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing


fun <T> whenever(methodCall: T): OngoingStubbing<T> = Mockito.`when`(methodCall)

@Suppress("UNCHECKED_CAST")
fun <T> nullAsT(): T = null as T

inline fun <reified T : Any> nonNullAny(): T {
    return Mockito.any(T::class.java) ?: nullAsT()
}