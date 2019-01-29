@file:Suppress("MemberVisibilityCanBePrivate", "DeprecatedCallableAddReplaceWith", "unused")

package com.lelloman.instrumentedtestutils

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.GeneralLocation
import android.support.test.espresso.action.GeneralSwipeAction
import android.support.test.espresso.action.Press
import android.support.test.espresso.action.Swipe
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.lelloman.instrumentedtestutils.matcher.AtPositionMatcher
import com.lelloman.instrumentedtestutils.matcher.RecyclerViewCountMatcher
import com.lelloman.instrumentedtestutils.matcher.SwipeRefreshLayoutMatcher
import org.hamcrest.BaseMatcher
import org.hamcrest.Description


@Deprecated(message = "Use ViewAssertions.checkViewIsDisplayed instead")
fun viewIsDisplayed(id: Int) {
    Espresso.onView(ViewMatchers.withId(id)).checkMatches(ViewMatchers.isDisplayed())
}

@Deprecated(message = "Use ViewAssertions.checkViewWithTextIsDisplayed instead")
fun viewWithTextIsDisplayed(text: String) {
    viewWithText(text).checkMatches(ViewMatchers.isDisplayed())
}

@Deprecated(message = "Use ViewAssertions instead")
fun checkIsSwipeRefreshing(isRefreshing: Boolean, id: Int)
        : ViewInteraction = viewWithId(id).checkMatches(SwipeRefreshLayoutMatcher(isRefreshing))

@Deprecated(message = "Use ViewAssertions instead")
fun checkRecyclerViewCount(count: Int, id: Int)
        : ViewInteraction = viewWithId(id).checkMatches(RecyclerViewCountMatcher(count))

@Deprecated(message = "Use ViewAssertions instead")
fun checkViewAtPositionHasText(position: Int, text: String, id: Int) {
    viewWithId(id)
        .check(
            ViewAssertions.matches(
                AtPositionMatcher(
                    position,
                    ViewMatchers.hasDescendant(ViewMatchers.withText(text))
                )
            )
        )
}

@Suppress("DEPRECATION")
@Deprecated(message = "Use ViewAssertions instead")
fun checkViewAtPositionHasImageVisible(position: Int, recyclerViewId: Int, imageViewId: Int) =
    checkViewAtPositionImageViewVisibility(
        position = position,
        recyclerViewId = recyclerViewId,
        imageViewId = imageViewId,
        visibility = View.VISIBLE
    )

@Suppress("DEPRECATION")
@Deprecated(message = "Use ViewAssertions instead")
fun checkViewAtPositionHasImageGone(position: Int, recyclerViewId: Int, imageViewId: Int) =
    checkViewAtPositionImageViewVisibility(
        position = position,
        recyclerViewId = recyclerViewId,
        imageViewId = imageViewId,
        visibility = View.GONE
    )

@Deprecated(message = "Use ViewAssertions instead")
fun checkViewAtPositionImageViewVisibility(
    position: Int,
    recyclerViewId: Int,
    imageViewId: Int,
    visibility: Int
) {
    viewWithId(recyclerViewId)
        .check(ViewAssertions.matches(AtPositionMatcher(position, object : BaseMatcher<View>() {
            override fun describeTo(description: Description?) = Unit

            override fun matches(item: Any?): Boolean {
                val view = item as View

                val imageView = view.findViewById<ImageView>(imageViewId)
                return imageView.visibility == visibility
            }
        })))
}

@Deprecated(message = "Use ViewActions instead")
fun swipeLeft(id: Int) {
    viewWithId(id).perform(
        GeneralSwipeAction(
            Swipe.FAST,
            GeneralLocation.CENTER_LEFT,
            GeneralLocation.CENTER_RIGHT,
            Press.FINGER
        )
    )
}

@Deprecated(message = "Use ViewActions instead")
fun swipeRight(id: Int) {
    viewWithId(id).perform(
        GeneralSwipeAction(
            Swipe.FAST,
            GeneralLocation.CENTER_RIGHT,
            GeneralLocation.CENTER_LEFT,
            Press.FINGER
        )
    )
}

@Deprecated(message = "Use ViewActions instead")
fun typeInEditText(id: Int, text: String) {
    viewWithId(id).perform(ViewActions.typeText(text))
}

@Deprecated(message = "Use ViewActions instead")
fun openOverflowMenu() {
    Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
}

@Deprecated(message = "Use ViewActions instead")
fun clickView(id: Int) {
    viewWithId(id).perform(ViewActions.click())
}

@Deprecated(message = "Use ViewActions instead")
fun clickViewWithText(text: String) {
    viewWithText(text).perform(ViewActions.click())
}

@Deprecated(message = "Use ViewActions instead")
fun clickOnRecyclerViewItem(position: Int, recyclerViewId: Int): ViewInteraction =
    viewWithId(recyclerViewId)
        .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position, ViewActions.click()))


object ViewAssertions {
    fun checkViewIsDisplayed(id: Int) {
        Espresso.onView(ViewMatchers.withId(id)).checkMatches(ViewMatchers.isDisplayed())
    }

    fun checkViewWithTextIsDisplayed(text: String) {
        viewWithText(text).checkMatches(ViewMatchers.isDisplayed())
    }

    fun checkIsSwipeRefreshing(isRefreshing: Boolean, id: Int)
            : ViewInteraction = viewWithId(id).checkMatches(SwipeRefreshLayoutMatcher(isRefreshing))

    fun checkRecyclerViewCount(count: Int, id: Int)
            : ViewInteraction = viewWithId(id).checkMatches(RecyclerViewCountMatcher(count))

    fun checkViewAtPositionHasText(position: Int, text: String, id: Int) {
        viewWithId(id)
            .check(
                ViewAssertions.matches(
                    AtPositionMatcher(
                        position,
                        ViewMatchers.hasDescendant(ViewMatchers.withText(text))
                    )
                )
            )
    }

    fun checkViewAtPositionHasImageVisible(position: Int, recyclerViewId: Int, imageViewId: Int) =
        checkViewAtPositionImageViewVisibility(
            position = position,
            recyclerViewId = recyclerViewId,
            imageViewId = imageViewId,
            visibility = View.VISIBLE
        )

    fun checkViewAtPositionHasImageGone(position: Int, recyclerViewId: Int, imageViewId: Int) =
        checkViewAtPositionImageViewVisibility(
            position = position,
            recyclerViewId = recyclerViewId,
            imageViewId = imageViewId,
            visibility = View.GONE
        )

    fun checkViewAtPositionImageViewVisibility(
        position: Int,
        recyclerViewId: Int,
        imageViewId: Int,
        visibility: Int
    ) {
        viewWithId(recyclerViewId)
            .check(ViewAssertions.matches(AtPositionMatcher(position, object : BaseMatcher<View>() {
                override fun describeTo(description: Description?) = Unit

                override fun matches(item: Any?): Boolean {
                    val view = item as View

                    val imageView = view.findViewById<ImageView>(imageViewId)
                    return imageView.visibility == visibility
                }
            })))
    }
}