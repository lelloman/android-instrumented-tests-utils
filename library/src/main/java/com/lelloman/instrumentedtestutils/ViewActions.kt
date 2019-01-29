@file:Suppress("DeprecatedCallableAddReplaceWith", "unused")

package com.lelloman.instrumentedtestutils

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.ViewAction
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.*
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.v7.widget.RecyclerView


@Deprecated(message = "Use ViewActions instead")
fun swipeBottomSheetUp(): ViewAction = GeneralSwipeAction(
    Swipe.FAST,
    CoordinatesProvider {
        floatArrayOf(200f, 1750f)
    },
    CoordinatesProvider {
        floatArrayOf(400f, 700f)
    },
    Press.FINGER
)


object ViewActions {
    fun swipeBottomSheetUp(): ViewAction = GeneralSwipeAction(
        Swipe.FAST,
        CoordinatesProvider {
            floatArrayOf(200f, 1750f)
        },
        CoordinatesProvider {
            floatArrayOf(400f, 700f)
        },
        Press.FINGER
    )

    fun clickOnRecyclerViewItem(position: Int, recyclerViewId: Int): ViewInteraction =
        viewWithId(recyclerViewId)
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position, ViewActions.click()))

    fun swipeLeft(id: Int) {
        viewWithId(id).perform(GeneralSwipeAction(
            Swipe.FAST,
            GeneralLocation.CENTER_LEFT,
            GeneralLocation.CENTER_RIGHT,
            Press.FINGER
        ))
    }

    fun swipeRight(id: Int) {
        viewWithId(id).perform(GeneralSwipeAction(
            Swipe.FAST,
            GeneralLocation.CENTER_RIGHT,
            GeneralLocation.CENTER_LEFT,
            Press.FINGER
        ))
    }

    fun typeInEditText(id: Int, text: String) {
        viewWithId(id).perform(ViewActions.typeText(text))
    }

    fun openOverflowMenu() {
        Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
    }

    fun clickView(id: Int) {
        viewWithId(id).perform(ViewActions.click())
    }

    fun clickViewWithText(text: String) {
        viewWithText(text).perform(ViewActions.click())
    }
}