package com.example.vedicremedies.data

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class PlanetRepositoryTest {

    @Test
    fun `planets list should contain all nine grahas`() {
        val planets = PlanetRepository.planets
        
        assertThat(planets).hasSize(9)
        
        val planetIds = planets.map { it.id }
        assertThat(planetIds).containsExactly(
            "sun", "moon", "mars", "mercury", "jupiter", 
            "venus", "saturn", "rahu", "ketu"
        )
    }

    @Test
    fun `each planet should have valid data structure`() {
        val planets = PlanetRepository.planets
        
        planets.forEach { planet ->
            assertThat(planet.id).isNotEmpty()
            assertThat(planet.name).isNotEmpty()
            assertThat(planet.sanskritName).isNotEmpty()
            assertThat(planet.archetype).isNotEmpty()
            assertThat(planet.attributes).isNotEmpty()
            assertThat(planet.afflictions).isNotEmpty()
        }
    }

    @Test
    fun `sun planet should have correct basic properties`() {
        val sun = PlanetRepository.planets.find { it.id == "sun" }
        
        assertThat(sun).isNotNull()
        assertThat(sun!!.name).isEqualTo("Sun")
        assertThat(sun.sanskritName).isEqualTo("Surya")
        assertThat(sun.archetype).contains("Soul")
        assertThat(sun.attributes).isNotEmpty()
        assertThat(sun.afflictions).hasSize(2)
    }

    @Test
    fun `moon planet should have correct basic properties`() {
        val moon = PlanetRepository.planets.find { it.id == "moon" }
        
        assertThat(moon).isNotNull()
        assertThat(moon!!.name).isEqualTo("Moon")
        assertThat(moon.sanskritName).isEqualTo("Chandra")
        assertThat(moon.archetype).contains("Mind")
        assertThat(moon.attributes).isNotEmpty()
        assertThat(moon.afflictions).hasSize(2)
    }

    @Test
    fun `each affliction should have complete remedy structure`() {
        val planets = PlanetRepository.planets
        
        planets.forEach { planet ->
            planet.afflictions.forEach { affliction ->
                assertThat(affliction.title).isNotEmpty()
                assertThat(affliction.description).isNotEmpty()
                assertThat(affliction.indicators).isNotEmpty()
                assertThat(affliction.remedies).isNotEmpty()
                
                affliction.remedies.forEach { remedy ->
                    assertThat(remedy.title).isNotEmpty()
                    assertThat(remedy.items).isNotEmpty()
                    remedy.items.forEach { item ->
                        assertThat(item).isNotEmpty()
                    }
                }
            }
        }
    }

    @Test
    fun `shadow planets rahu and ketu should be present`() {
        val planets = PlanetRepository.planets
        val rahu = planets.find { it.id == "rahu" }
        val ketu = planets.find { it.id == "ketu" }
        
        assertThat(rahu).isNotNull()
        assertThat(ketu).isNotNull()
        assertThat(rahu!!.name).isEqualTo("Rahu")
        assertThat(ketu!!.name).isEqualTo("Ketu")
    }

    @Test
    fun `all remedy categories should have meaningful titles`() {
        val planets = PlanetRepository.planets
        val remedyTitles = mutableSetOf<String>()
        
        planets.forEach { planet ->
            planet.afflictions.forEach { affliction ->
                affliction.remedies.forEach { remedy ->
                    remedyTitles.add(remedy.title)
                }
            }
        }
        
        assertThat(remedyTitles).contains("Mantra")
        assertThat(remedyTitles).contains("Charity")
        assertThat(remedyTitles).contains("Lifestyle")
        assertThat(remedyTitles.size).isAtLeast(10)
    }

    @Test
    fun `planet attributes should provide meaningful information`() {
        val planets = PlanetRepository.planets
        
        planets.forEach { planet ->
            assertThat(planet.attributes.size).isAtLeast(3)
            planet.attributes.forEach { attribute ->
                assertThat(attribute.length).isAtLeast(10)
            }
        }
    }

    @Test
    fun `affliction indicators should be descriptive`() {
        val planets = PlanetRepository.planets
        
        planets.forEach { planet ->
            planet.afflictions.forEach { affliction ->
                assertThat(affliction.indicators.size).isAtLeast(3)
                affliction.indicators.forEach { indicator ->
                    assertThat(indicator.length).isAtLeast(5)
                }
            }
        }
    }
}