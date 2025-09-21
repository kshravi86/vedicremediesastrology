package com.example.vedicremedies

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class FavoritesFunctionalityTest {

    private fun toggleFavorite(
        favorites: Set<String>,
        afflictionTitle: String
    ): Set<String> {
        return if (favorites.contains(afflictionTitle)) {
            favorites - afflictionTitle
        } else {
            favorites + afflictionTitle
        }
    }

    @Test
    fun `empty favorites set initially`() {
        val favorites = setOf<String>()
        assertThat(favorites).isEmpty()
    }

    @Test
    fun `adding favorite to empty set works correctly`() {
        val favorites = setOf<String>()
        val afflictionTitle = "Kuja Dosha"
        
        val updatedFavorites = toggleFavorite(favorites, afflictionTitle)
        
        assertThat(updatedFavorites).hasSize(1)
        assertThat(updatedFavorites).contains(afflictionTitle)
    }

    @Test
    fun `removing favorite from set works correctly`() {
        val afflictionTitle = "Kuja Dosha"
        val favorites = setOf(afflictionTitle)
        
        val updatedFavorites = toggleFavorite(favorites, afflictionTitle)
        
        assertThat(updatedFavorites).isEmpty()
        assertThat(updatedFavorites).doesNotContain(afflictionTitle)
    }

    @Test
    fun `adding multiple favorites works correctly`() {
        var favorites = setOf<String>()
        val afflictions = listOf("Kuja Dosha", "Sade Sati", "Venus Retrograde")
        
        afflictions.forEach { affliction ->
            favorites = toggleFavorite(favorites, affliction)
        }
        
        assertThat(favorites).hasSize(3)
        assertThat(favorites).containsExactlyElementsIn(afflictions)
    }

    @Test
    fun `removing one favorite keeps others intact`() {
        val afflictions = listOf("Kuja Dosha", "Sade Sati", "Venus Retrograde")
        var favorites = afflictions.toSet()
        
        favorites = toggleFavorite(favorites, "Sade Sati")
        
        assertThat(favorites).hasSize(2)
        assertThat(favorites).contains("Kuja Dosha")
        assertThat(favorites).contains("Venus Retrograde")
        assertThat(favorites).doesNotContain("Sade Sati")
    }

    @Test
    fun `toggling same favorite twice returns to original state`() {
        val favorites = setOf("Kuja Dosha")
        val afflictionTitle = "Sade Sati"
        
        val afterAdd = toggleFavorite(favorites, afflictionTitle)
        val afterRemove = toggleFavorite(afterAdd, afflictionTitle)
        
        assertThat(afterRemove).isEqualTo(favorites)
    }

    @Test
    fun `favorites are case sensitive`() {
        var favorites = setOf<String>()
        
        favorites = toggleFavorite(favorites, "Kuja Dosha")
        favorites = toggleFavorite(favorites, "kuja dosha")
        
        assertThat(favorites).hasSize(2)
        assertThat(favorites).contains("Kuja Dosha")
        assertThat(favorites).contains("kuja dosha")
    }

    @Test
    fun `checking if affliction is favorite works correctly`() {
        val favorites = setOf("Kuja Dosha", "Sade Sati")
        
        assertThat(favorites.contains("Kuja Dosha")).isTrue()
        assertThat(favorites.contains("Sade Sati")).isTrue()
        assertThat(favorites.contains("Venus Retrograde")).isFalse()
        assertThat(favorites.contains("Non-existent")).isFalse()
    }

    @Test
    fun `favorites with special characters work correctly`() {
        var favorites = setOf<String>()
        val specialAfflictions = listOf(
            "Rahu in the 8th house",
            "Saturn-Mars Opposition",
            "Jupiter-Rahu conjunct"
        )
        
        specialAfflictions.forEach { affliction ->
            favorites = toggleFavorite(favorites, affliction)
        }
        
        assertThat(favorites).hasSize(3)
        assertThat(favorites).containsExactlyElementsIn(specialAfflictions)
    }

    @Test
    fun `large number of favorites handled correctly`() {
        var favorites = setOf<String>()
        val manyAfflictions = (1..100).map { "Affliction $it" }
        
        manyAfflictions.forEach { affliction ->
            favorites = toggleFavorite(favorites, affliction)
        }
        
        assertThat(favorites).hasSize(100)
        assertThat(favorites).containsExactlyElementsIn(manyAfflictions)
    }

    @Test
    fun `empty string as affliction title handled correctly`() {
        var favorites = setOf<String>()
        
        favorites = toggleFavorite(favorites, "")
        
        assertThat(favorites).hasSize(1)
        assertThat(favorites).contains("")
        
        favorites = toggleFavorite(favorites, "")
        assertThat(favorites).isEmpty()
    }

    @Test
    fun `whitespace-only affliction title handled correctly`() {
        var favorites = setOf<String>()
        val whitespaceTitle = "   "
        
        favorites = toggleFavorite(favorites, whitespaceTitle)
        
        assertThat(favorites).hasSize(1)
        assertThat(favorites).contains(whitespaceTitle)
    }

    @Test
    fun `unicode characters in affliction titles work correctly`() {
        var favorites = setOf<String>()
        val unicodeAfflictions = listOf(
            "Śani Sāḍe Sātī",
            "Guru-Candra Yoga",
            "Maṅgala Doṣa"
        )
        
        unicodeAfflictions.forEach { affliction ->
            favorites = toggleFavorite(favorites, affliction)
        }
        
        assertThat(favorites).hasSize(3)
        assertThat(favorites).containsExactlyElementsIn(unicodeAfflictions)
    }
}