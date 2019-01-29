@file:Suppress("unused")

package com.lelloman.instrumentedtestutils

import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.test.InstrumentationRegistry


abstract class Screen {

    private val context = InstrumentationRegistry.getTargetContext()

    protected fun viewVisible(@IdRes id: Int) = ViewAssertions.checkViewIsDisplayed(id)
    protected fun viewVisible(text: String) = ViewAssertions.checkViewWithTextIsDisplayed(text)

    protected fun string(@StringRes id: Int): String = context.getString(id)
    protected fun string(@StringRes id: Int, vararg arg: Any): String = context.getString(id, *arg)
}