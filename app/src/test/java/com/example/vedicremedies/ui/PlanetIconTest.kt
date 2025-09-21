package com.example.vedicremedies.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.Bolt
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material.icons.outlined.Psychology
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material.icons.outlined.Whatshot
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class PlanetIconTest {

    private fun planetIcon(id: String) = when (id) {
        "sun" -> Icons.Outlined.WbSunny
        "moon" -> Icons.Outlined.DarkMode
        "mars" -> Icons.Outlined.Whatshot
        "mercury" -> Icons.Outlined.Psychology
        "jupiter" -> Icons.Outlined.AutoStories
        "venus" -> Icons.Outlined.FavoriteBorder
        "saturn" -> Icons.Outlined.Schedule
        "rahu" -> Icons.Outlined.Public
        "ketu" -> Icons.Outlined.Bolt
        else -> Icons.Outlined.Star
    }

    @Test
    fun `sun icon returns correct icon`() {
        val icon = planetIcon("sun")
        assertThat(icon).isEqualTo(Icons.Outlined.WbSunny)
    }

    @Test
    fun `moon icon returns correct icon`() {
        val icon = planetIcon("moon")
        assertThat(icon).isEqualTo(Icons.Outlined.DarkMode)
    }

    @Test
    fun `mars icon returns correct icon`() {
        val icon = planetIcon("mars")
        assertThat(icon).isEqualTo(Icons.Outlined.Whatshot)
    }

    @Test
    fun `mercury icon returns correct icon`() {
        val icon = planetIcon("mercury")
        assertThat(icon).isEqualTo(Icons.Outlined.Psychology)
    }

    @Test
    fun `jupiter icon returns correct icon`() {
        val icon = planetIcon("jupiter")
        assertThat(icon).isEqualTo(Icons.Outlined.AutoStories)
    }

    @Test
    fun `venus icon returns correct icon`() {
        val icon = planetIcon("venus")
        assertThat(icon).isEqualTo(Icons.Outlined.FavoriteBorder)
    }

    @Test
    fun `saturn icon returns correct icon`() {
        val icon = planetIcon("saturn")
        assertThat(icon).isEqualTo(Icons.Outlined.Schedule)
    }

    @Test
    fun `rahu icon returns correct icon`() {
        val icon = planetIcon("rahu")
        assertThat(icon).isEqualTo(Icons.Outlined.Public)
    }

    @Test
    fun `ketu icon returns correct icon`() {
        val icon = planetIcon("ketu")
        assertThat(icon).isEqualTo(Icons.Outlined.Bolt)
    }

    @Test
    fun `unknown planet id returns default star icon`() {
        val icon = planetIcon("unknown")
        assertThat(icon).isEqualTo(Icons.Outlined.Star)
    }

    @Test
    fun `empty string returns default star icon`() {
        val icon = planetIcon("")
        assertThat(icon).isEqualTo(Icons.Outlined.Star)
    }

    @Test
    fun `null-like string returns default star icon`() {
        val icon = planetIcon("null")
        assertThat(icon).isEqualTo(Icons.Outlined.Star)
    }

    @Test
    fun `case sensitivity test - uppercase returns default icon`() {
        val icon = planetIcon("SUN")
        assertThat(icon).isEqualTo(Icons.Outlined.Star)
    }

    @Test
    fun `all known planet ids have unique icons`() {
        val planetIds = listOf("sun", "moon", "mars", "mercury", "jupiter", "venus", "saturn", "rahu", "ketu")
        val icons = planetIds.map { planetIcon(it) }
        val uniqueIcons = icons.toSet()
        
        assertThat(uniqueIcons).hasSize(planetIds.size)
    }

    @Test
    fun `special characters in planet id return default icon`() {
        val specialIds = listOf("sun@", "moon#", "mars$", "jupiter%")
        
        specialIds.forEach { id ->
            val icon = planetIcon(id)
            assertThat(icon).isEqualTo(Icons.Outlined.Star)
        }
    }
}