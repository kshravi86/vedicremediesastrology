package com.example.vedicremedies.ui

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class LayoutTypeTest {

    private enum class LayoutType { Compact, Medium, Expanded }

    private fun getLayoutType(screenWidthDp: Int): LayoutType {
        return when {
            screenWidthDp >= 840 -> LayoutType.Expanded
            screenWidthDp >= 600 -> LayoutType.Medium
            else -> LayoutType.Compact
        }
    }

    @Test
    fun `compact layout for small screens`() {
        val layoutType = getLayoutType(400)
        assertThat(layoutType).isEqualTo(LayoutType.Compact)
    }

    @Test
    fun `compact layout at boundary below 600dp`() {
        val layoutType = getLayoutType(599)
        assertThat(layoutType).isEqualTo(LayoutType.Compact)
    }

    @Test
    fun `medium layout at exact 600dp boundary`() {
        val layoutType = getLayoutType(600)
        assertThat(layoutType).isEqualTo(LayoutType.Medium)
    }

    @Test
    fun `medium layout for tablet screens`() {
        val layoutType = getLayoutType(700)
        assertThat(layoutType).isEqualTo(LayoutType.Medium)
    }

    @Test
    fun `medium layout at boundary below 840dp`() {
        val layoutType = getLayoutType(839)
        assertThat(layoutType).isEqualTo(LayoutType.Medium)
    }

    @Test
    fun `expanded layout at exact 840dp boundary`() {
        val layoutType = getLayoutType(840)
        assertThat(layoutType).isEqualTo(LayoutType.Expanded)
    }

    @Test
    fun `expanded layout for large screens`() {
        val layoutType = getLayoutType(1200)
        assertThat(layoutType).isEqualTo(LayoutType.Expanded)
    }

    @Test
    fun `compact layout for very small screens`() {
        val layoutType = getLayoutType(320)
        assertThat(layoutType).isEqualTo(LayoutType.Compact)
    }

    @Test
    fun `expanded layout for very large screens`() {
        val layoutType = getLayoutType(2000)
        assertThat(layoutType).isEqualTo(LayoutType.Expanded)
    }

    @Test
    fun `zero width returns compact layout`() {
        val layoutType = getLayoutType(0)
        assertThat(layoutType).isEqualTo(LayoutType.Compact)
    }

    @Test
    fun `negative width returns compact layout`() {
        val layoutType = getLayoutType(-100)
        assertThat(layoutType).isEqualTo(LayoutType.Compact)
    }

    @Test
    fun `layout types cover all common device widths`() {
        val commonWidths = mapOf(
            320 to LayoutType.Compact,   // Small phone
            360 to LayoutType.Compact,   // Standard phone
            411 to LayoutType.Compact,   // Large phone
            600 to LayoutType.Medium,    // Small tablet
            768 to LayoutType.Medium,    // Medium tablet
            840 to LayoutType.Expanded,  // Large tablet
            1024 to LayoutType.Expanded, // Desktop
            1440 to LayoutType.Expanded  // Large desktop
        )

        commonWidths.forEach { (width, expectedLayout) ->
            val actualLayout = getLayoutType(width)
            assertThat(actualLayout).isEqualTo(expectedLayout)
        }
    }
}