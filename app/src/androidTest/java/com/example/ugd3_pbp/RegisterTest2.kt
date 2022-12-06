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
class RegisterTest2 {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(Register::class.java)

    @Test
    fun registerTest2() {
        val neumorphButton = onView(
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
        neumorphButton.perform(click())
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
        textInputEditText.perform(replaceText("test"), closeSoftKeyboard())

        val neumorphButton2 = onView(
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
        neumorphButton2.perform(click())
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
        textInputEditText2.perform(replaceText("test"), closeSoftKeyboard())

        val neumorphButton3 = onView(
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
        neumorphButton3.perform(click())
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
        textInputEditText3.perform(replaceText("test"), closeSoftKeyboard())

        val neumorphButton4 = onView(
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
        neumorphButton4.perform(click())
        onView(isRoot()).perform(waitFor(3000))

        val textInputEditText4 = onView(
            allOf(
                withId(R.id.email2), withText("test"),
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
                withId(R.id.email2), withText("test"),
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
        textInputEditText5.perform(replaceText("test@gmail.com"))

        val textInputEditText6 = onView(
            allOf(
                withId(R.id.email2), withText("test@gmail.com"),
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

        val neumorphButton5 = onView(
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
        neumorphButton5.perform(click())
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
        textInputEditText7.perform(replaceText("12/10/20"), closeSoftKeyboard())

        val neumorphButton6 = onView(
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
        neumorphButton6.perform(click())
        onView(isRoot()).perform(waitFor(3000))

        val textInputEditText8 = onView(
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
        textInputEditText8.perform(replaceText("09876532"), closeSoftKeyboard())

        val neumorphButton7 = onView(
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
        neumorphButton7.perform(click())
        onView(isRoot()).perform(waitFor(3000))

        val textInputEditText9 = onView(
            allOf(
                withId(R.id.phone2), withText("09876532"),
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
        textInputEditText9.perform(click())

        val textInputEditText10 = onView(
            allOf(
                withId(R.id.phone2), withText("09876532"),
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
        textInputEditText10.perform(replaceText("098765321232"))

        val textInputEditText11 = onView(
            allOf(
                withId(R.id.phone2), withText("098765321232"),
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
        textInputEditText11.perform(closeSoftKeyboard())

        val neumorphButton8 = onView(
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
        neumorphButton8.perform(click())
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
