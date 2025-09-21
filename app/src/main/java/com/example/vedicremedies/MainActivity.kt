package com.example.vedicremedies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.Bolt
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material.icons.outlined.Psychology
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.Timeline
import androidx.compose.material.icons.outlined.Whatshot
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.vedicremedies.data.Affliction
import com.example.vedicremedies.data.PlanetRemedy
import com.example.vedicremedies.data.PlanetRepository
import com.example.vedicremedies.data.RemedyCategory
import com.example.vedicremedies.ui.theme.VedicRemediesTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VedicRemediesTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    RemediesApp()
                }
            }
        }
    }
}

private enum class LayoutType { Compact, Medium, Expanded }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemediesApp() {
    val planets = remember { PlanetRepository.planets }
    var selectedPlanetId by rememberSaveable { mutableStateOf(planets.first().id) }
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var isSearchActive by rememberSaveable { mutableStateOf(false) }
    var favoriteAfflictions by rememberSaveable { mutableStateOf(setOf<String>()) }
    
    val selectedPlanet = remember(selectedPlanetId, planets) {
        planets.firstOrNull { it.id == selectedPlanetId } ?: planets.first()
    }
    
    val filteredPlanets = remember(planets, searchQuery) {
        if (searchQuery.isBlank()) {
            planets
        } else {
            planets.filter { planet ->
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
        }
    }
    
    val layoutType = rememberLayoutType()

    when (layoutType) {
        LayoutType.Compact -> CompactLayout(
            planets = planets,
            filteredPlanets = filteredPlanets,
            selectedPlanet = selectedPlanet,
            searchQuery = searchQuery,
            isSearchActive = isSearchActive,
            favoriteAfflictions = favoriteAfflictions,
            onPlanetSelected = { selectedPlanetId = it },
            onSearchQueryChange = { searchQuery = it },
            onSearchActiveChange = { isSearchActive = it },
            onToggleFavorite = { afflictionTitle ->
                favoriteAfflictions = if (favoriteAfflictions.contains(afflictionTitle)) {
                    favoriteAfflictions - afflictionTitle
                } else {
                    favoriteAfflictions + afflictionTitle
                }
            }
        )
        LayoutType.Medium, LayoutType.Expanded -> RailLayout(
            planets = planets,
            filteredPlanets = filteredPlanets,
            selectedPlanet = selectedPlanet,
            searchQuery = searchQuery,
            isSearchActive = isSearchActive,
            favoriteAfflictions = favoriteAfflictions,
            onPlanetSelected = { selectedPlanetId = it },
            onSearchQueryChange = { searchQuery = it },
            onSearchActiveChange = { isSearchActive = it },
            onToggleFavorite = { afflictionTitle ->
                favoriteAfflictions = if (favoriteAfflictions.contains(afflictionTitle)) {
                    favoriteAfflictions - afflictionTitle
                } else {
                    favoriteAfflictions + afflictionTitle
                }
            }
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CompactLayout(
    planets: List<PlanetRemedy>,
    filteredPlanets: List<PlanetRemedy>,
    selectedPlanet: PlanetRemedy,
    searchQuery: String,
    isSearchActive: Boolean,
    favoriteAfflictions: Set<String>,
    onPlanetSelected: (String) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onSearchActiveChange: (Boolean) -> Unit,
    onToggleFavorite: (String) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            PlanetDrawerContent(
                planets = if (isSearchActive) filteredPlanets else planets,
                selectedPlanetId = selectedPlanet.id,
                searchQuery = searchQuery,
                isSearchActive = isSearchActive,
                onPlanetSelected = {
                    onPlanetSelected(it)
                    scope.launch { drawerState.close() }
                },
                onSearchQueryChange = onSearchQueryChange,
                onSearchActiveChange = onSearchActiveChange
            )
        }
    ) {
        Scaffold(
            topBar = {
                LargeTopAppBar(
                    title = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Vedic Planetary Remedies",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Personalized upayas for each graha",
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(imageVector = Icons.Outlined.Menu, contentDescription = "Open menu")
                        }
                    },
                    actions = {
                        IconButton(onClick = { onSearchActiveChange(!isSearchActive) }) {
                            Icon(
                                imageVector = if (isSearchActive) Icons.Outlined.Clear else Icons.Outlined.Search,
                                contentDescription = if (isSearchActive) "Close search" else "Search"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.largeTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        ) { innerPadding ->
            PlanetDetailScreen(
                planet = selectedPlanet,
                favoriteAfflictions = favoriteAfflictions,
                onToggleFavorite = onToggleFavorite,
                contentPadding = innerPadding
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RailLayout(
    planets: List<PlanetRemedy>,
    filteredPlanets: List<PlanetRemedy>,
    selectedPlanet: PlanetRemedy,
    searchQuery: String,
    isSearchActive: Boolean,
    favoriteAfflictions: Set<String>,
    onPlanetSelected: (String) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onSearchActiveChange: (Boolean) -> Unit,
    onToggleFavorite: (String) -> Unit
) {
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Vedic Planetary Remedies",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Explore remedies, rituals, and seva",
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { onSearchActiveChange(!isSearchActive) }) {
                        Icon(
                            imageVector = if (isSearchActive) Icons.Outlined.Clear else Icons.Outlined.Search,
                            contentDescription = if (isSearchActive) "Close search" else "Search"
                        )
                    }
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize()) {
            AnimatedVisibility(
                visible = isSearchActive,
                enter = slideInVertically() + fadeIn(),
                exit = slideOutVertically() + fadeOut()
            ) {
                SearchBar(
                    searchQuery = searchQuery,
                    onSearchQueryChange = onSearchQueryChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = innerPadding.calculateTopPadding())
                )
            }
            
            Row(modifier = Modifier.fillMaxSize()) {
                PlanetNavigationRail(
                    planets = if (isSearchActive) filteredPlanets else planets,
                    selectedPlanetId = selectedPlanet.id,
                    onPlanetSelected = onPlanetSelected
                )
                PlanetDetailScreen(
                    planet = selectedPlanet,
                    favoriteAfflictions = favoriteAfflictions,
                    onToggleFavorite = onToggleFavorite,
                    contentPadding = if (isSearchActive) PaddingValues(top = 16.dp) else innerPadding,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
@Composable
private fun PlanetDrawerContent(
    planets: List<PlanetRemedy>,
    selectedPlanetId: String,
    searchQuery: String,
    isSearchActive: Boolean,
    onPlanetSelected: (String) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onSearchActiveChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(top = 32.dp, bottom = 16.dp)
            .animateContentSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isSearchActive && searchQuery.isNotBlank()) "Search Results" else "Nine Grahas",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            IconButton(onClick = { onSearchActiveChange(!isSearchActive) }) {
                Icon(
                    imageVector = if (isSearchActive) Icons.Outlined.Clear else Icons.Outlined.Search,
                    contentDescription = if (isSearchActive) "Close search" else "Search"
                )
            }
        }
        
        AnimatedVisibility(
            visible = isSearchActive,
            enter = slideInVertically() + fadeIn(),
            exit = slideOutVertically() + fadeOut()
        ) {
            SearchBar(
                searchQuery = searchQuery,
                onSearchQueryChange = onSearchQueryChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            )
        }
        planets.forEach { planet ->
            AnimatedVisibility(
                visible = true,
                enter = fadeIn(animationSpec = tween(300)) + scaleIn(animationSpec = tween(300)),
                exit = fadeOut(animationSpec = tween(300)) + scaleOut(animationSpec = tween(300))
            ) {
                NavigationDrawerItem(
                    label = { Text(planet.name) },
                    selected = planet.id == selectedPlanetId,
                    onClick = { onPlanetSelected(planet.id) },
                    icon = {
                        Icon(
                            imageVector = planetIcon(planet.id),
                            contentDescription = null
                        )
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                    ),
                    modifier = Modifier.animateContentSize()
                )
            }
        }
        
        if (planets.isEmpty() && isSearchActive) {
            Text(
                text = "No remedies found for '$searchQuery'",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
private fun PlanetNavigationRail(
    planets: List<PlanetRemedy>,
    selectedPlanetId: String,
    onPlanetSelected: (String) -> Unit
) {
    NavigationRail(
        modifier = Modifier
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.surfaceVariant),
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(
            modifier = Modifier.padding(vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            planets.forEach { planet ->
                NavigationRailItem(
                    selected = planet.id == selectedPlanetId,
                    onClick = { onPlanetSelected(planet.id) },
                    icon = {
                        Icon(
                            imageVector = planetIcon(planet.id),
                            contentDescription = planet.name
                        )
                    },
                    label = { Text(text = planet.name) },
                    alwaysShowLabel = false
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PlanetDetailScreen(
    planet: PlanetRemedy,
    favoriteAfflictions: Set<String>,
    onToggleFavorite: (String) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.surface,
                        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                        MaterialTheme.colorScheme.background
                    ),
                    start = Offset.Zero,
                    end = Offset(0f, Float.POSITIVE_INFINITY)
                )
            )
    ) {
        LazyColumn(
            contentPadding = PaddingValues(
                top = contentPadding.calculateTopPadding() + 24.dp,
                bottom = contentPadding.calculateBottomPadding() + 32.dp,
                start = 24.dp,
                end = 24.dp
            ),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                PlanetHeroSection(planet = planet)
            }
            item {
                Text(
                    text = "Afflictions & Remedies",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            item {
                if (planet.challenges.isNotEmpty()) {
                    ChallengesSection(challenges = planet.challenges)
                }
            }
            items(planet.afflictions) { affliction ->
                AfflictionCard(
                    affliction = affliction,
                    isFavorite = favoriteAfflictions.contains(affliction.title),
                    onToggleFavorite = { onToggleFavorite(affliction.title) }
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PlanetHeroSection(planet: PlanetRemedy) {
    ElevatedCard(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    modifier = Modifier.size(72.dp),
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = planetIcon(planet.id),
                            contentDescription = planet.name,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "${planet.name} - ${planet.sanskritName}",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = planet.archetype,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Ruling Deity: ${planet.rulingDeity}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            
            // Mantras Section - Always visible
            Text(
                text = "Sacred Mantras",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(6.dp))
            
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Veda Vyasa Mantra",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = planet.vedaVyasaMantra,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Beej Mantra",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = planet.beejMantra,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Gemstone and Metal info - Always visible
            Row {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Gemstone",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = planet.gemstone,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Metal",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = planet.metal,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Positive Traits - Always visible
            Text(
                text = "Positive Traits",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(top = 8.dp)
            ) {
                planet.positiveTraits.forEach { trait ->
                    AssistChip(
                        onClick = {},
                        label = { Text(text = trait) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.CheckCircle,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                        },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            labelColor = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Yogas - Always visible
            if (planet.yogas.isNotEmpty()) {
                Text(
                    text = "Important Yogas",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold
                )
                Column(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    planet.yogas.forEach { yoga ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Outlined.Star,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = yoga,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun InfoChip(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    AssistChip(
        onClick = {},
        label = { Text(text = label, style = MaterialTheme.typography.labelSmall) },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(14.dp)
            )
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = MaterialTheme.colorScheme.surface,
            labelColor = MaterialTheme.colorScheme.onSurface
        )
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ChallengesSection(challenges: List<String>) {
    ElevatedCard(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.7f),
            contentColor = MaterialTheme.colorScheme.onErrorContainer
        )
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "Potential Challenges",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Areas that may need attention when this planet is weak or afflicted",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 4.dp, bottom = 12.dp)
            )
            
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                challenges.forEach { challenge ->
                    AssistChip(
                        onClick = {},
                        label = { Text(text = challenge) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Clear,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                        },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            labelColor = MaterialTheme.colorScheme.onSurface
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun AfflictionCard(
    affliction: Affliction,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    ElevatedCard(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        modifier = Modifier
            .animateContentSize(animationSpec = spring())
            .clickable { expanded = !expanded }
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = affliction.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = affliction.description,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                Row {
                    IconButton(onClick = onToggleFavorite) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
                            tint = if (isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            imageVector = if (expanded) Icons.Outlined.Timeline else Icons.Outlined.Schedule,
                            contentDescription = if (expanded) "Collapse" else "Expand"
                        )
                    }
                }
            }

            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(animationSpec = spring()) + fadeIn(),
                exit = shrinkVertically(animationSpec = spring()) + fadeOut()
            ) {
                Column {
                    IndicatorsSection(indicators = affliction.indicators)
                    RemediesSection(remedies = affliction.remedies)
                }
            }
        }
    }
}

@Composable
private fun IndicatorsSection(indicators: List<String>) {
    Text(
        text = "Indicators",
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(top = 12.dp)
    )
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        indicators.forEach { indicator ->
            BulletRow(text = indicator)
        }
    }
}

@Composable
private fun RemediesSection(remedies: List<RemedyCategory>) {
    Text(
        text = "Remedies",
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(top = 16.dp)
    )
    Column(modifier = Modifier.padding(top = 8.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        remedies.forEach { category ->
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = category.title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Medium
                    )
                    Column(
                        modifier = Modifier.padding(top = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        category.items.forEach { item ->
                            BulletRow(text = item)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BulletRow(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Outlined.CheckCircle,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(18.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
private fun SearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        placeholder = { Text("Search remedies, planets, afflictions...") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = "Search"
            )
        },
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                IconButton(onClick = { onSearchQueryChange("") }) {
                    Icon(
                        imageVector = Icons.Outlined.Clear,
                        contentDescription = "Clear search"
                    )
                }
            }
        },
        singleLine = true,
        modifier = modifier
    )
}

@Composable
private fun rememberLayoutType(): LayoutType {
    val configuration = LocalConfiguration.current
    val widthDp = configuration.screenWidthDp
    return when {
        widthDp >= 840 -> LayoutType.Expanded
        widthDp >= 600 -> LayoutType.Medium
        else -> LayoutType.Compact
    }
}

@Composable
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
