package com.rickyslash.composetestingapp.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.rickyslash.composetestingapp.ui.theme.ComposeTestingAppTheme
import com.rickyslash.composetestingapp.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorAppTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    /* this is using createComposeRule()
    val composeTestRule = createComposeRule()*/

    @Before
    fun setUp() {
        composeTestRule.setContent {
            ComposeTestingAppTheme {
                CalculatorApp()
            }
        }
    }

    @Test
    fun calculate_area_of_rectangle_correct() {
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.enter_length)).performTextInput("3")
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.enter_width)).performTextInput("4")
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.count)).performClick()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.count), useUnmergedTree = true).assertHasNoClickAction()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.result, 12.0)).assertExists()
    }

    @Test
    fun wrong_input_not_calculated() {
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.enter_length)).performTextInput("..3")
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.enter_width)).performTextInput("4")
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.count)).performClick()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.result, 0.0)).assertExists()
    }

    /* this is using createComposeRule()
    @Test
    fun calculate_area_of_rectangle_correct() {
        composeTestRule.onNodeWithText("Enter length").performTextInput("3")
        composeTestRule.onNodeWithText("Enter width").performTextInput("4")
        composeTestRule.onNodeWithText("Calculate!").performClick()
        composeTestRule.onNodeWithText("Result: 12.0").assertExists()
    }*/

}