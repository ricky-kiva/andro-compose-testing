package com.rickyslash.composetestingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rickyslash.composetestingapp.ui.CalculatorApp
import com.rickyslash.composetestingapp.ui.theme.ComposeTestingAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestingAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CalculatorApp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTestingAppTheme {

    }
}

// 2 type of UI testing:
// - Screen UI Test: test single page (clicking Button, write on TextField, or checking visible element)
// - User Flow Test / Navigation Test: test navigation between page

// Good Testing:
// - covers many scenario, such:
// --- condition in accordance
// --- condition not in accordance
// --- wrong input
// --- external factor is disrupting (no connection, insufficient memory, etc)
// - consistent: always output same value (not flaky)
// --- avoid using external server or different locale
// - focus: make the coverage as small as possible
// - test the behavior: test still success when there is change in code implementation
// - fast: test is as fast as possible
// - concise: test is as compact as possible

// 2 option in making Compose Rule:
// - createComposeRule: make specific rule for Composable Function (ComposeContentTestRule)
// - createComposeAndroidRule: make rule that have access to Activity (AndroidComposeTestRule)

// dependency for Compose Testing API:
// Test rules: androidTestImplementation("androidx.compose.ui:ui-test-junit4:$compose_version")
// createComposeRule (NOT createAndroidComposeRule): debugImplementation("androidx.compose.ui:ui-test-manifest:$compose_version")

// ComposeContentTestRule enables to run any Composable without the help of Android Framework (whole app, page, component, etc)

// Compose Testing API, 3 main component to do Testing with it:
// - Finder: find element (node) that will be tested / given action
// - Action: add event to do action (example: click, text input, etc)
// - Assertion: check condition/state of UI

// basic implementation of testing:
// - onNode() -> performClick() -> Assert()

/* example complete Compose Testing:
class MyComposeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            MyAppTheme {
                MainScreen(uiState = fakeUiState, /*...*/)
            }
        }
    }

    @Test
    fun myTest() {
        composeTestRule.onNodeWithText("Continue").performClick()
        composeTestRule.onNodeWithText("Welcome").assertIsDisplayed()
    }
}*/

// it is supported to search metadata of an element using Finder:
// - onNodeWithText: search by text
// - onNodeWithContentDescription: search by ContentDescription
// - onNodeWithTag: search by tag (could be tagged using modifier testTag)
// - onNode(Matcher): search based on Matcher

// example command for Action:
// - performTextInput: do text input
// - performClick: do Click
// - performScrollToIndex: do scroll to certain index

// example command for Assertion:
// - assertIs[Not]Displayed: check item is displayed on Screen or not
// - assert[DoesNot]Exist: check item is existing or not
// - assertHas[No]ClickAction: check item has click action or not
// - assertIs[Not]Enabled: check item is active or not
// - assertEquals: check value is the same
// - assert(Matcher): check whether it's the same with matcher