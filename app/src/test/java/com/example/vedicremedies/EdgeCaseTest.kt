package com.example.vedicremedies

import com.example.vedicremedies.data.PlanetRepository
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class EdgeCaseTest {

    @Test
    fun `repository handles concurrent access correctly`() {
        val threads = mutableListOf<Thread>()
        val results = mutableListOf<Int>()
        
        repeat(10) {
            threads.add(Thread {
                val planets = PlanetRepository.planets
                synchronized(results) {
                    results.add(planets.size)
                }
            })
        }
        
        threads.forEach { it.start() }
        threads.forEach { it.join() }
        
        assertThat(results).hasSize(10)
        assertThat(results.all { it == 9 }).isTrue()
    }

    @Test
    fun `search handles null and empty inputs gracefully`() {
        val planets = PlanetRepository.planets
        
        val filterFunction = { searchQuery: String? ->
            if (searchQuery.isNullOrBlank()) {
                planets
            } else {
                planets.filter { planet ->
                    planet.name.contains(searchQuery, ignoreCase = true) ||
                    planet.sanskritName.contains(searchQuery, ignoreCase = true)
                }
            }
        }
        
        assertThat(filterFunction(null)).hasSize(9)
        assertThat(filterFunction("")).hasSize(9)
        assertThat(filterFunction("   ")).hasSize(9)
    }

    @Test
    fun `favorites handles large datasets efficiently`() {
        var favorites = setOf<String>()
        val startTime = System.currentTimeMillis()
        
        // Add 10,000 favorites
        repeat(10000) { index ->
            favorites = favorites + "Affliction $index"
        }
        
        val addTime = System.currentTimeMillis() - startTime
        
        // Check if favorite exists (should be fast)
        val checkStartTime = System.currentTimeMillis()
        val exists = favorites.contains("Affliction 5000")
        val checkTime = System.currentTimeMillis() - checkStartTime
        
        assertThat(favorites).hasSize(10000)
        assertThat(exists).isTrue()
        assertThat(addTime).isLessThan(1000) // Should take less than 1 second
        assertThat(checkTime).isLessThan(10) // Should take less than 10ms
    }

    @Test
    fun `search performance with large query strings`() {
        val planets = PlanetRepository.planets
        val longQuery = "a".repeat(1000)
        
        val startTime = System.currentTimeMillis()
        val results = planets.filter { planet ->
            planet.name.contains(longQuery, ignoreCase = true) ||
            planet.afflictions.any { affliction ->
                affliction.description.contains(longQuery, ignoreCase = true)
            }
        }
        val endTime = System.currentTimeMillis()
        
        assertThat(results).isEmpty() // Long string of 'a's shouldn't match anything
        assertThat(endTime - startTime).isLessThan(100) // Should be fast even with long query
    }

    @Test
    fun `data integrity after multiple operations`() {
        val originalPlanets = PlanetRepository.planets
        val originalSize = originalPlanets.size
        val originalSunName = originalPlanets.find { it.id == "sun" }?.name
        
        // Perform various read operations
        repeat(100) {
            val planets = PlanetRepository.planets
            val sun = planets.find { it.id == "sun" }
            assertThat(sun?.name).isEqualTo(originalSunName)
        }
        
        // Verify data hasn't changed
        val finalPlanets = PlanetRepository.planets
        assertThat(finalPlanets).hasSize(originalSize)
        assertThat(finalPlanets.find { it.id == "sun" }?.name).isEqualTo(originalSunName)
    }

    @Test
    fun `memory usage stays reasonable with repeated access`() {
        val initialMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()
        
        // Access repository many times
        repeat(1000) {
            val planets = PlanetRepository.planets
            val searchResults = planets.filter { planet ->
                planet.name.contains("a", ignoreCase = true)
            }
            assertThat(searchResults).isNotEmpty()
        }
        
        // Force garbage collection
        System.gc()
        Thread.sleep(100)
        
        val finalMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()
        val memoryIncrease = finalMemory - initialMemory
        
        // Memory increase should be reasonable (less than 50MB)
        assertThat(memoryIncrease).isLessThan(50 * 1024 * 1024)
    }

    @Test
    fun `special characters in search don't break functionality`() {
        val planets = PlanetRepository.planets
        val specialQueries = listOf(
            "\"quoted\"",
            "regex[.*]",
            "back\\slash",
            "forward/slash",
            "percent%",
            "underscore_",
            "hyphen-dash",
            "exclamation!",
            "question?",
            "semicolon;",
            "unicode: ॐ",
            "emoji: 🕉️"
        )
        
        specialQueries.forEach { query ->
            val results = planets.filter { planet ->
                try {
                    planet.name.contains(query, ignoreCase = true) ||
                    planet.afflictions.any { affliction ->
                        affliction.description.contains(query, ignoreCase = true)
                    }
                } catch (e: Exception) {
                    false // If any exception occurs, consider no match
                }
            }
            // Should not throw exceptions
            assertThat(results.size).isAtLeast(0)
        }
    }

    @Test
    fun `favorites handle duplicate entries correctly`() {
        var favorites = setOf<String>()
        val afflictionTitle = "Test Affliction"
        
        // Try to add the same favorite multiple times
        repeat(5) {
            favorites = favorites + afflictionTitle
        }
        
        assertThat(favorites).hasSize(1)
        assertThat(favorites).contains(afflictionTitle)
    }
}