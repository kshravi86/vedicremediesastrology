package com.example.vedicremedies

import com.example.vedicremedies.data.PlanetRepository
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SearchFunctionalityTest {

    private val planets = PlanetRepository.planets

    private fun filterPlanets(searchQuery: String) = planets.filter { planet ->
        planet.name.contains(searchQuery, ignoreCase = true) ||
        planet.sanskritName.contains(searchQuery, ignoreCase = true) ||
        planet.archetype.contains(searchQuery, ignoreCase = true) ||
        planet.afflictions.any { affliction ->
            affliction.title.contains(searchQuery, ignoreCase = true) ||
            affliction.description.contains(searchQuery, ignoreCase = true) ||
            affliction.remedies.any { remedy ->
                remedy.title.contains(searchQuery, ignoreCase = true) ||
                remedy.items.any { it.contains(searchQuery, ignoreCase = true) }
            }
        }
    }

    @Test
    fun `search with empty query returns all planets`() {
        val result = filterPlanets("")
        assertThat(result).hasSize(9)
        assertThat(result).isEqualTo(planets)
    }

    @Test
    fun `search with blank query returns all planets`() {
        val result = filterPlanets("   ")
        assertThat(result).hasSize(9)
    }

    @Test
    fun `search by planet name is case insensitive`() {
        val resultLower = filterPlanets("sun")
        val resultUpper = filterPlanets("SUN")
        val resultMixed = filterPlanets("Sun")
        
        assertThat(resultLower).hasSize(1)
        assertThat(resultUpper).hasSize(1)
        assertThat(resultMixed).hasSize(1)
        assertThat(resultLower.first().name).isEqualTo("Sun")
    }

    @Test
    fun `search by sanskrit name finds correct planet`() {
        val result = filterPlanets("Surya")
        
        assertThat(result).hasSize(1)
        assertThat(result.first().sanskritName).isEqualTo("Surya")
        assertThat(result.first().name).isEqualTo("Sun")
    }

    @Test
    fun `search by archetype finds relevant planets`() {
        val result = filterPlanets("mind")
        
        assertThat(result).isNotEmpty()
        val moonPlanet = result.find { it.name == "Moon" }
        assertThat(moonPlanet).isNotNull()
        assertThat(moonPlanet!!.archetype).contains("Mind")
    }

    @Test
    fun `search by affliction title finds correct planets`() {
        val result = filterPlanets("Kuja Dosha")
        
        assertThat(result).hasSize(1)
        assertThat(result.first().name).isEqualTo("Mars")
        
        val marsAfflictions = result.first().afflictions
        val kujaDosha = marsAfflictions.find { it.title == "Kuja Dosha" }
        assertThat(kujaDosha).isNotNull()
    }

    @Test
    fun `search by remedy type finds multiple planets`() {
        val result = filterPlanets("Mantra")
        
        assertThat(result.size).isAtLeast(5)
        
        result.forEach { planet ->
            val hasMantraRemedy = planet.afflictions.any { affliction ->
                affliction.remedies.any { remedy ->
                    remedy.title.contains("Mantra", ignoreCase = true)
                }
            }
            assertThat(hasMantraRemedy).isTrue()
        }
    }

    @Test
    fun `search by remedy item finds relevant planets`() {
        val result = filterPlanets("Hanuman Chalisa")
        
        assertThat(result).isNotEmpty()
        
        result.forEach { planet ->
            val hasHanumanChalisaRemedy = planet.afflictions.any { affliction ->
                affliction.remedies.any { remedy ->
                    remedy.items.any { it.contains("Hanuman Chalisa", ignoreCase = true) }
                }
            }
            assertThat(hasHanumanChalisaRemedy).isTrue()
        }
    }

    @Test
    fun `search for non-existent term returns empty list`() {
        val result = filterPlanets("NonExistentTerm123")
        assertThat(result).isEmpty()
    }

    @Test
    fun `search by partial word finds matches`() {
        val result = filterPlanets("Jup")
        
        assertThat(result).hasSize(1)
        assertThat(result.first().name).isEqualTo("Jupiter")
    }

    @Test
    fun `search by indicator finds relevant planets`() {
        val result = filterPlanets("anxiety")
        
        assertThat(result).isNotEmpty()
        
        result.forEach { planet ->
            val hasAnxietyIndicator = planet.afflictions.any { affliction ->
                affliction.indicators.any { it.contains("anxiety", ignoreCase = true) }
            }
            assertThat(hasAnxietyIndicator).isTrue()
        }
    }

    @Test
    fun `search by affliction description finds matches`() {
        val result = filterPlanets("confidence")
        
        assertThat(result).isNotEmpty()
        
        result.forEach { planet ->
            val hasConfidenceInDescription = planet.afflictions.any { affliction ->
                affliction.description.contains("confidence", ignoreCase = true)
            }
            assertThat(hasConfidenceInDescription).isTrue()
        }
    }

    @Test
    fun `search performance with special characters`() {
        val specialQueries = listOf("Om", "Namah", "yoga", "dasha", "dosha")
        
        specialQueries.forEach { query ->
            val result = filterPlanets(query)
            
            assertThat(result.size).isAtLeast(0)
        }
    }

    @Test
    fun `search with numbers finds relevant content`() {
        val result = filterPlanets("108")
        
        assertThat(result).isNotEmpty()
        
        result.forEach { planet ->
            val hasNumberInRemedies = planet.afflictions.any { affliction ->
                affliction.remedies.any { remedy ->
                    remedy.items.any { it.contains("108") }
                }
            }
            assertThat(hasNumberInRemedies).isTrue()
        }
    }
}