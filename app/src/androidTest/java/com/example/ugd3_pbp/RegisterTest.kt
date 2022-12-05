package com.example.ugd3_pbp


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class RegisterTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(Register::class.java)

    @Test
    fun registerTest() {
        val materialButton = onView(
            allOf(
                withId(R.id.btnRegis), withText("Register"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout),
                        childAtPosition(
                            withId(R.id.registerLayout),
                            2
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())
        onView(isRoot()).perform(waitFor(3000))

        val textInputEditText = onView(
            allOf(
                withId(R.id.username2),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.username),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("xhcf"), closeSoftKeyboard())



        val materialButton2 = onView(
            allOf(
                withId(R.id.btnRegis), withText("Register"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout),
                        childAtPosition(
                            withId(R.id.registerLayout),
                            2
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())
        onView(isRoot()).perform(waitFor(3000))

        val textInputEditText2 = onView(
            allOf(
                withId(R.id.password2),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.password),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText2.perform(replaceText("hejee"), closeSoftKeyboard())

        val materialButton3 = onView(
            allOf(
                withId(R.id.btnRegis), withText("Register"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout),
                        childAtPosition(
                            withId(R.id.registerLayout),
                            2
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton3.perform(click())
        onView(isRoot()).perform(waitFor(3000))

        val textInputEditText3 = onView(
            allOf(
                withId(R.id.email2),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.email),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText3.perform(replaceText("hdieje"), closeSoftKeyboard())

        val materialButton4 = onView(
            allOf(
                withId(R.id.btnRegis), withText("Register"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout),
                        childAtPosition(
                            withId(R.id.registerLayout),
                            2
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton4.perform(click())
        onView(isRoot()).perform(waitFor(3000))

        val textInputEditText4 = onView(
            allOf(
                withId(R.id.email2), withText("hdieje"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.email),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText4.perform(click())

        val textInputEditText5 = onView(
            allOf(
                withId(R.id.email2), withText("hdieje"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.email),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText5.perform(replaceText("hdieje@gmail.com"))

        val textInputEditText6 = onView(
            allOf(
                withId(R.id.email2), withText("hdieje@gmail.com"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.email),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText6.perform(closeSoftKeyboard())

        val materialButton5 = onView(
            allOf(
                withId(R.id.btnRegis), withText("Register"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout),
                        childAtPosition(
                            withId(R.id.registerLayout),
                            2
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton5.perform(click())
        onView(isRoot()).perform(waitFor(3000))

        val textInputEditText7 = onView(
            allOf(
                withId(R.id.date2),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.date),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText7.perform(click())

        val textInputEditText8 = onView(
            allOf(
                withId(R.id.date2),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.date),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText8.perform(click())

        val textInputEditText9 = onView(
            allOf(
                withId(R.id.date2),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.date),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText9.perform(replaceText("2-05-2002"), closeSoftKeyboard())

        val textInputEditText10 = onView(
            allOf(
                withId(R.id.date2), withText("2-05-2002"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.date),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText10.perform(click())

        val textInputEditText11 = onView(
            allOf(
                withId(R.id.date2), withText("2-05-2002"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.date),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText11.perform(replaceText("02-05-2002"))

        val textInputEditText12 = onView(
            allOf(
                withId(R.id.date2), withText("02-05-2002"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.date),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText12.perform(closeSoftKeyboard())

        val materialButton6 = onView(
            allOf(
                withId(R.id.btnRegis), withText("Register"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout),
                        childAtPosition(
                            withId(R.id.registerLayout),
                            2
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton6.perform(click())
        onView(isRoot()).perform(waitFor(3000))

        val textInputEditText13 = onView(
            allOf(
                withId(R.id.phone2),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.phone),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText13.perform(replaceText("646494"), closeSoftKeyboard())

        val materialButton7 = onView(
            allOf(
                withId(R.id.btnRegis), withText("Register"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout),
                        childAtPosition(
                            withId(R.id.registerLayout),
                            2
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton7.perform(click())
        onView(isRoot()).perform(waitFor(3000))

        val textInputEditText14 = onView(
            allOf(
                withId(R.id.phone2), withText("646494"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.phone),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText14.perform(click())

        val textInputEditText15 = onView(
            allOf(
                withId(R.id.phone2), withText("646494"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.phone),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText15.perform(replaceText("081266727612"))

        val textInputEditText16 = onView(
            allOf(
                withId(R.id.phone2), withText("081266727612"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.phone),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText16.perform(closeSoftKeyboard())

        val materialButton8 = onView(
            allOf(
                withId(R.id.btnRegis), withText("Register"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout),
                        childAtPosition(
                            withId(R.id.registerLayout),
                            2
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton8.perform(click())
        onView(isRoot()).perform(waitFor(3000))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }

    fun waitFor(delay: Long): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isRoot()
            }

            override fun getDescription(): String {
                return "wait for" + delay + "miliseconds"
            }

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadForAtLeast(delay)
            }
        }
    }
}
