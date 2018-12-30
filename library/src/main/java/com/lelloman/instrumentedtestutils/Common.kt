package com.lelloman.instrumentedtestutils

import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing


fun <T> whenever(methodCall: T): OngoingStubbing<T> = Mockito.`when`(methodCall)