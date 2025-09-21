package com.example.vedicremedies

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.vedicremedies.ui.theme.VedicRemediesTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchUITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun searchBarAppearsWhenSearchIconClicked() {
        composeTestRule.setContent {
            VedicRemediesTheme {
                RemediesApp()
            }
        }

        composeTestRule
            .onNodeWithContentDescription("Search")
            .performClick()

        composeTestRule
            .onNodeWithText("Search remedies, planets, afflictions...")
            .assertIsDisplayed()
    }

    @Test
    fun searchBarAcceptsTextInput() {
        composeTestRule.setContent {
            VedicRemediesTheme {
                RemediesApp()
            }
        }

        // Open search
        composeTestRule
            .onNodeWithContentDescription("Search")
            .performClick()

        // Type in search bar
        composeTestRule
            .onNodeWithText("Search remedies, planets, afflictions...")
            .performTextInput("Mars")

        // Verify text is entered (the search bar should now show the entered text)
        composeTestRule
            .onNodeWithText("Mars")
            .assertIsDisplayed()
    }

    @Test
    fun clearSearchIconAppearsAfterTyping() {
        composeTestRule.setContent {
            VedicRemediesTheme {
                RemediesApp()
            }
        }

        // Open search
        composeTestRule
            .onNodeWithContentDescription("Search")
            .performClick()

        // Type in search bar
        composeTestRule
            .onNodeWithText("Search remedies, planets, afflictions...")
            .performTextInput("Sun")

        // Clear icon should appear
        composeTestRule
            .onNodeWithContentDescription("Clear search")
            .assertIsDisplayed()
    }

    @Test
    fun closeSearchIconChangesAfterOpening() {
        composeTestRule.setContent {
            VedicRemediesTheme {
                RemediesApp()
            }
        }

        // Initially should show search icon
        composeTestRule
            .onNodeWithContentDescription("Search")
            .assertIsDisplayed()

        // Click to open search
        composeTestRule
            .onNodeWithContentDescription("Search")
            .performClick()

        // Should now show close icon
        composeTestRule
            .onNodeWithContentDescription("Close search")
            .assertIsDisplayed()
    }

    @Test
    fun searchWorksForPlanetNames() {
        composeTestRule.setContent {
            VedicRemediesTheme {
                RemediesApp()
            }
        }

        // Open search
        composeTestRule
            .onNodeWithContentDescription("Search")
            .performClick()

        // Search for Mars
        composeTestRule
            .onNodeWithText("Search remedies, planets, afflictions...")
            .performTextInput("Mars")

        // Open drawer to see search results
        composeTestRule
            .onNodeWithContentDescription("Open menu")
            .performClick()

        // Mars should be visible in the drawer
        composeTestRule
            .onNodeWithText("Mars")
            .assertIsDisplayed()
    }
}