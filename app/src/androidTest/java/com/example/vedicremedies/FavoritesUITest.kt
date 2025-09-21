package com.example.vedicremedies

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.vedicremedies.ui.theme.VedicRemediesTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FavoritesUITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun favoriteIconIsDisplayedOnAfflictionCards() {
        composeTestRule.setContent {
            VedicRemediesTheme {
                RemediesApp()
            }
        }

        // Should show unfilled heart icon initially
        composeTestRule
            .onNodeWithContentDescription("Add to favorites")
            .assertIsDisplayed()
    }

    @Test
    fun clickingFavoriteIconChangesState() {
        composeTestRule.setContent {
            VedicRemediesTheme {
                RemediesApp()
            }
        }

        // Click the favorite button
        composeTestRule
            .onNodeWithContentDescription("Add to favorites")
            .performClick()

        // Should now show filled heart icon
        composeTestRule
            .onNodeWithContentDescription("Remove from favorites")
            .assertIsDisplayed()
    }

    @Test
    fun clickingFavoriteIconTwiceReturnsToOriginalState() {
        composeTestRule.setContent {
            VedicRemediesTheme {
                RemediesApp()
            }
        }

        // Click to add to favorites
        composeTestRule
            .onNodeWithContentDescription("Add to favorites")
            .performClick()

        // Click again to remove from favorites
        composeTestRule
            .onNodeWithContentDescription("Remove from favorites")
            .performClick()

        // Should be back to unfilled heart
        composeTestRule
            .onNodeWithContentDescription("Add to favorites")
            .assertIsDisplayed()
    }

    @Test
    fun expandCollapseIconIsDisplayedOnAfflictionCards() {
        composeTestRule.setContent {
            VedicRemediesTheme {
                RemediesApp()
            }
        }

        // Should show expand icon initially (cards start collapsed)
        composeTestRule
            .onNodeWithContentDescription("Expand")
            .assertIsDisplayed()
    }

    @Test
    fun clickingExpandIconChangesToCollapseIcon() {
        composeTestRule.setContent {
            VedicRemediesTheme {
                RemediesApp()
            }
        }

        // Click the expand button
        composeTestRule
            .onNodeWithContentDescription("Expand")
            .performClick()

        // Should now show collapse icon
        composeTestRule
            .onNodeWithContentDescription("Collapse")
            .assertIsDisplayed()
    }
}