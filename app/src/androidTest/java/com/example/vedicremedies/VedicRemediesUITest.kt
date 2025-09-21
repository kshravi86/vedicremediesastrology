package com.example.vedicremedies

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.vedicremedies.ui.theme.VedicRemediesTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VedicRemediesUITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun appDisplaysCorrectTitle() {
        composeTestRule.setContent {
            VedicRemediesTheme {
                RemediesApp()
            }
        }

        composeTestRule
            .onNodeWithText("Vedic Planetary Remedies")
            .assertIsDisplayed()
    }

    @Test
    fun appDisplaysSubtitle() {
        composeTestRule.setContent {
            VedicRemediesTheme {
                RemediesApp()
            }
        }

        composeTestRule
            .onNodeWithText("Personalized upayas for each graha")
            .assertIsDisplayed()
    }

    @Test
    fun menuIconIsDisplayed() {
        composeTestRule.setContent {
            VedicRemediesTheme {
                RemediesApp()
            }
        }

        composeTestRule
            .onNodeWithContentDescription("Open menu")
            .assertIsDisplayed()
    }

    @Test
    fun searchIconIsDisplayed() {
        composeTestRule.setContent {
            VedicRemediesTheme {
                RemediesApp()
            }
        }

        composeTestRule
            .onNodeWithContentDescription("Search")
            .assertIsDisplayed()
    }

    @Test
    fun clickingSearchIconShowsSearchBar() {
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
    fun afflictionsAndRemediesSectionIsDisplayed() {
        composeTestRule.setContent {
            VedicRemediesTheme {
                RemediesApp()
            }
        }

        composeTestRule
            .onNodeWithText("Afflictions & Remedies")
            .assertIsDisplayed()
    }

    @Test
    fun planetNameIsDisplayed() {
        composeTestRule.setContent {
            VedicRemediesTheme {
                RemediesApp()
            }
        }

        // Sun should be the first planet displayed
        composeTestRule
            .onNodeWithText("Sun - Surya")
            .assertIsDisplayed()
    }

    @Test
    fun planetArchetypeIsDisplayed() {
        composeTestRule.setContent {
            VedicRemediesTheme {
                RemediesApp()
            }
        }

        composeTestRule
            .onNodeWithText("Soul, vitality, authority")
            .assertIsDisplayed()
    }

    @Test
    fun keyGiftsSectionIsDisplayed() {
        composeTestRule.setContent {
            VedicRemediesTheme {
                RemediesApp()
            }
        }

        composeTestRule
            .onNodeWithText("Key Gifts")
            .assertIsDisplayed()
    }
}