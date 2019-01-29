@file:Suppress("unused")

package com.lelloman.instrumentedtestutils

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.view.View
import org.hamcrest.Matcher
import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing


fun <T> whenever(methodCall: T): OngoingStubbing<T> = Mockito.`when`(methodCall)

@Suppress("UNCHECKED_CAST")
fun <T> nullAsT(): T = null as T

inline fun <reified T : Any> nonNullAny(): T {
    return Mockito.any(T::class.java) ?: nullAsT()
}

fun ViewInteraction.checkMatches(matcher: Matcher<View>)
        : ViewInteraction = check(ViewAssertions.matches(matcher))

fun onUiThread(action: () -> Unit) = InstrumentationRegistry
    .getInstrumentation()
    .runOnMainSync(action)

fun wait(seconds: Double) = Thread.sleep((seconds * 1000).toLong())

fun viewWithId(id: Int): ViewInteraction = Espresso.onView(ViewMatchers.withId(id))

fun viewWithText(text: String): ViewInteraction = Espresso.onView(ViewMatchers.withText(text))