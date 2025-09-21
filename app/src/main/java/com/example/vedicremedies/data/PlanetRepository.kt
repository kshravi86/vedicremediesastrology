package com.example.vedicremedies.data

// Domain models describing planetary afflictions and appropriate remedial measures.
data class RemedyCategory(
    val title: String,
    val items: List<String>
)

data class Affliction(
    val title: String,
    val description: String,
    val indicators: List<String>,
    val remedies: List<RemedyCategory>
)

data class PlanetRemedy(
    val id: String,
    val name: String,
    val sanskritName: String,
    val archetype: String,
    val attributes: List<String>,
    val vedaVyasaMantra: String,
    val beejMantra: String,
    val element: String,
    val day: String,
    val gemstone: String,
    val metal: String,
    val color: String,
    val positiveTraits: List<String>,
    val challenges: List<String>,
    val rulingDeity: String,
    val yogas: List<String>,
    val afflictions: List<Affliction>
)

// Static catalogue covering the nine grahas from classical Vedic astrology.
object PlanetRepository {
    val planets: List<PlanetRemedy> = listOf(
        PlanetRemedy(
            id = "sun",
            name = "Sun",
            sanskritName = "Surya",
            archetype = "Soul, vitality, authority",
            attributes = listOf(
                "Represents the atman (soul) and life force",
                "Rules leadership, confidence, father energy",
                "Karaka for health, eyesight, career status"
            ),
            vedaVyasaMantra = "Japaa kusuma sankaasham kaashyapeyam mahaadyutim |\nTamorim sarva paapaghnam pranato'smi divaakaram ||",
            beejMantra = "ॐ ह्राम् ह्रीम् ह्राम् सह सूर्याय नमः",
            element = "Fire (Agni)",
            day = "Sunday (Ravivar)",
            gemstone = "Ruby (Manikya), Red Coral",
            metal = "Gold (Swarna)",
            color = "Orange, Red, Golden Yellow",
            positiveTraits = listOf(
                "Natural leadership and authority",
                "Strong willpower and determination", 
                "Generous and magnanimous nature",
                "Good health and vitality",
                "Fame and recognition",
                "Strong connection with father"
            ),
            challenges = listOf(
                "Ego and pride issues",
                "Arrogance and dominating nature",
                "Heart and eye related problems",
                "Conflicts with authority figures",
                "Excessive anger and aggression"
            ),
            rulingDeity = "Surya Bhagavan, Aditya",
            yogas = listOf(
                "Budhaditya Yoga (with Mercury)",
                "Nipuna Yoga (when exalted)",
                "Ravi Yoga (strong placement)"
            ),
            afflictions = listOf(
                Affliction(
                    title = "Debilitated in Libra",
                    description = "When Surya occupies Libra, confidence and self-worth can waver, impacting health and recognition.",
                    indicators = listOf(
                        "Lack of direction or purpose",
                        "Chronic fatigue and low immunity",
                        "Strained relationship with father figures"
                    ),
                    remedies = listOf(
                        RemedyCategory(
                            title = "Mantra",
                            items = listOf(
                                "Chant the Aditya Hridayam daily at sunrise",
                                "Recite 'Om Hram Hrim Hraum Sah Suryaya Namah' 108 times"
                            )
                        ),
                        RemedyCategory(
                            title = "Lifestyle",
                            items = listOf(
                                "Perform Surya Namaskar while facing the rising sun",
                                "Offer water (Arghya) to the Sun using a copper vessel"
                            )
                        ),
                        RemedyCategory(
                            title = "Charity",
                            items = listOf(
                                "Donate wheat, jaggery, and copper on Sundays",
                                "Support educational initiatives for the visually impaired"
                            )
                        )
                    )
                ),
                Affliction(
                    title = "Combust Mercury",
                    description = "Close proximity of Mercury to the Sun can scorch intellect and decision-making clarity.",
                    indicators = listOf(
                        "Overconfidence leading to errors",
                        "Difficulties in conveying ideas",
                        "Headaches aggravated by stress"
                    ),
                    remedies = listOf(
                        RemedyCategory(
                            title = "Sadhana",
                            items = listOf(
                                "Practice tratak (steady gazing) on a ghee lamp",
                                "Meditate on the solar plexus chakra with golden light visualization"
                            )
                        ),
                        RemedyCategory(
                            title = "Ritual",
                            items = listOf(
                                "Light a sesame oil lamp at dawn for Surya",
                                "Observe a fast on Sundays until sunset"
                            )
                        )
                    )
                )
            )
        ),
        PlanetRemedy(
            id = "moon",
            name = "Moon",
            sanskritName = "Chandra",
            archetype = "Mind, emotions, nourishment",
            attributes = listOf(
                "Controls emotional resilience and intuition",
                "Signals maternal bonds and mental peace",
                "Influences fluids, sleep, and memory"
            ),
            vedaVyasaMantra = "Dadhi shankha tushaaraabham ksheerodaarnava sambhavam |\nNamaami shashinam somam shambhor mukuta bhooshanam ||",
            beejMantra = "ॐ श्राम् श्रीम् श्राम् सह चन्द्राय नमः",
            element = "Water (Jal)",
            day = "Monday (Somvar)",
            gemstone = "Pearl (Moti), Moonstone",
            metal = "Silver (Rajat)",
            color = "White, Silver, Cream",
            positiveTraits = listOf(
                "Emotional intelligence and empathy",
                "Strong intuition and psychic abilities",
                "Nurturing and caring nature",
                "Good memory and imagination",
                "Peaceful and harmonious disposition",
                "Strong connection with mother"
            ),
            challenges = listOf(
                "Emotional instability and mood swings",
                "Over-sensitivity and vulnerability",
                "Mental anxiety and depression",
                "Digestive and water retention issues",
                "Dependency and clingy behavior"
            ),
            rulingDeity = "Chandra Deva, Soma",
            yogas = listOf(
                "Gaja Kesari Yoga (with Jupiter)",
                "Chandra Mangal Yoga (with Mars)",
                "Sunapha Yoga (planets before Moon)"
            ),
            afflictions = listOf(
                Affliction(
                    title = "Waning Moon with Saturn",
                    description = "Chandra conjoined with Saturn can chill emotions and produce anxiety or low mood.",
                    indicators = listOf(
                        "Persistent worry and insomnia",
                        "Difficulty feeling cared for",
                        "Digestive issues linked to anxiety"
                    ),
                    remedies = listOf(
                        RemedyCategory(
                            title = "Mantra",
                            items = listOf(
                                "Chant 'Om Som Somaya Namah' on Mondays",
                                "Recite Sri Lalita Sahasranamam for emotional balance"
                            )
                        ),
                        RemedyCategory(
                            title = "Nutrition",
                            items = listOf(
                                "Include cooling foods like milk, rice, and cucumber",
                                "Avoid late-night stimulants, promote lunar fasting"
                            )
                        ),
                        RemedyCategory(
                            title = "Charity",
                            items = listOf(
                                "Donate white clothing or rice to mothers or caretakers",
                                "Offer kheer or sweet rice at a nearby temple"
                            )
                        )
                    )
                ),
                Affliction(
                    title = "Kemadruma Yoga",
                    description = "When Moon lacks planetary support on both sides, loneliness and instability emerge.",
                    indicators = listOf(
                        "Feeling unsupported or isolated",
                        "Fluctuating finances and moods",
                        "Cluttered home environment"
                    ),
                    remedies = listOf(
                        RemedyCategory(
                            title = "Ritual",
                            items = listOf(
                                "Perform Abhishekam to Shiva with milk on Mondays",
                                "Keep a silver bowl of water under moonlight and sip",
                                "Maintain a dedicated altar for Moon with white flowers"
                            )
                        ),
                        RemedyCategory(
                            title = "Seva",
                            items = listOf(
                                "Serve elderly women or caretakers",
                                "Support mental health initiatives"
                            )
                        )
                    )
                )
            )
        ),
        PlanetRemedy(
            id = "mars",
            name = "Mars",
            sanskritName = "Mangala",
            archetype = "Courage, drive, protection",
            attributes = listOf(
                "Symbolizes discipline, stamina, and assertiveness",
                "Governs blood, muscles, and siblings",
                "Promotes strategic action when balanced"
            ),
            vedaVyasaMantra = "Dharanee garbha sambhootam vidyut kaanti samaprabham |\nKumaaram shakti hastam tam mangalam pranamaamyaham ||",
            beejMantra = "ॐ क्राम् क्रीम् क्राम् सह भौमाय नमः",
            element = "Fire (Agni)",
            day = "Tuesday (Mangalvar)",
            gemstone = "Red Coral (Moonga), Carnelian",
            metal = "Copper (Tamra)",
            color = "Red, Orange, Scarlet",
            positiveTraits = listOf(
                "Courage and fearlessness",
                "Physical strength and energy",
                "Leadership in battle and competition",
                "Protective nature towards family",
                "Goal-oriented and determined",
                "Technical and mechanical skills"
            ),
            challenges = listOf(
                "Excessive anger and aggression",
                "Impatience and impulsiveness",
                "Conflicts and arguments",
                "Accidents and injuries",
                "Blood-related disorders",
                "Marital discord (Mangal Dosha)"
            ),
            rulingDeity = "Bhaumya, Angaraka, Kuja",
            yogas = listOf(
                "Ruchaka Yoga (exalted placement)",
                "Chandra Mangal Yoga (with Moon)",
                "Parivartana Yoga (exchange with other planets)"
            ),
            afflictions = listOf(
                Affliction(
                    title = "Kuja Dosha",
                    description = "An aggravated Mars in certain houses stresses relationships and sparks conflict.",
                    indicators = listOf(
                        "Impatience or argumentative tendencies",
                        "Delay or friction in marriage",
                        "Heat-related ailments or inflammation"
                    ),
                    remedies = listOf(
                        RemedyCategory(
                            title = "Mantra",
                            items = listOf(
                                "Chant 'Om Kraam Kreem Kraum Sah Bhaumaya Namah' on Tuesdays",
                                "Recite Hanuman Chalisa with devotion"
                            )
                        ),
                        RemedyCategory(
                            title = "Discipline",
                            items = listOf(
                                "Practice martial arts or structured physical training",
                                "Perform seva at temples of Subramanya or Hanuman"
                            )
                        ),
                        RemedyCategory(
                            title = "Charity",
                            items = listOf(
                                "Donate red lentils, tools, or first-aid supplies",
                                "Support causes for veterans or accident victims"
                            )
                        )
                    )
                ),
                Affliction(
                    title = "Retrograde Mars",
                    description = "Retrogression internalizes anger, causing hesitation or misdirected energy.",
                    indicators = listOf(
                        "Projects started but seldom finished",
                        "Suppressed anger that erupts unexpectedly",
                        "Muscle stiffness or cramps"
                    ),
                    remedies = listOf(
                        RemedyCategory(
                            title = "Chanting",
                            items = listOf(
                                "Recite 'Om Angarakaya Namah' with coral mala",
                                "Perform Narasimha Kavacham for protection"
                            )
                        ),
                        RemedyCategory(
                            title = "Therapy",
                            items = listOf(
                                "Channel energy into disciplined fitness",
                                "Adopt journaling to release suppressed emotions"
                            )
                        )
                    )
                )
            )
        ),
        PlanetRemedy(
            id = "mercury",
            name = "Mercury",
            sanskritName = "Budha",
            archetype = "Intellect, communication, commerce",
            attributes = listOf(
                "Regulates nervous system and speech",
                "Supports analytics, negotiation, entrepreneurship",
                "Karaka for education and adaptability"
            ),
            vedaVyasaMantra = "Priyangu kalikaa shyaamam roopenaa pratimam budham |\nSaumyam saumya gunopetam tam budham pranamaamyaham ||",
            beejMantra = "ॐ ब्राम् ब्रीम् ब्राम् सह बुधाय नमः",
            element = "Earth (Prithvi)",
            day = "Wednesday (Budhvar)",
            gemstone = "Emerald (Panna), Green Tourmaline",
            metal = "Bronze, Brass",
            color = "Green, Yellow-Green",
            positiveTraits = listOf(
                "Excellent communication skills",
                "Sharp intellect and analytical mind",
                "Business acumen and trading skills",
                "Adaptability and versatility",
                "Good memory and learning ability",
                "Mathematical and logical thinking"
            ),
            challenges = listOf(
                "Nervous anxiety and mental stress",
                "Speech disorders or communication issues",
                "Skin problems and allergies",
                "Restlessness and hyperactivity",
                "Scattered thoughts and lack of focus",
                "Respiratory problems"
            ),
            rulingDeity = "Budha, Vishnu",
            yogas = listOf(
                "Budhaditya Yoga (with Sun)",
                "Bhadra Yoga (exalted placement)",
                "Kahala Yoga (benefic aspect)"
            ),
            afflictions = listOf(
                Affliction(
                    title = "Afflicted by Rahu",
                    description = "Budha entangled with Rahu obscures judgment and multiplies distractions.",
                    indicators = listOf(
                        "Scattered thoughts, difficulty finishing tasks",
                        "Compulsive gadgets use, misinformation",
                        "Respiratory allergies or skin eruptions"
                    ),
                    remedies = listOf(
                        RemedyCategory(
                            title = "Mantra",
                            items = listOf(
                                "Chant 'Om Bum Budhaya Namah' on Wednesdays",
                                "Read Vishnu Sahasranamam for clarity"
                            )
                        ),
                        RemedyCategory(
                            title = "Detox",
                            items = listOf(
                                "Unplug from screens for the first hour each morning",
                                "Adopt pranayama like Nadi Shodhana twice daily"
                            )
                        ),
                        RemedyCategory(
                            title = "Service",
                            items = listOf(
                                "Teach literacy or mentor students",
                                "Donate stationery and books to schools"
                            )
                        )
                    )
                ),
                Affliction(
                    title = "Mercury Retrograde",
                    description = "Communications revisit older karmas; attention to detail becomes vital.",
                    indicators = listOf(
                        "Miscommunication, delayed travel",
                        "Tech glitches or contract revisions",
                        "Anxiety about decisions"
                    ),
                    remedies = listOf(
                        RemedyCategory(
                            title = "Practices",
                            items = listOf(
                                "Keep a mercury journal to track commitments",
                                "Recite Saraswati Vandana before key meetings"
                            )
                        ),
                        RemedyCategory(
                            title = "Offerings",
                            items = listOf(
                                "Offer green gram and tulsi to Vishnu on Wednesdays",
                                "Wear emerald or green tourmaline after consultation"
                            )
                        )
                    )
                )
            )
        ),
        PlanetRemedy(
            id = "jupiter",
            name = "Jupiter",
            sanskritName = "Guru",
            archetype = "Wisdom, expansion, grace",
            attributes = listOf(
                "Represents teachers, ethics, spiritual insight",
                "Guides wealth, progeny, optimism",
                "Rules liver, endocrine system, knowledge"
            ),
            vedaVyasaMantra = "Devaanaam cha risheenaam cha gurum kaanchana sannibham |\nBuddhi bhootam trilokesham tam namaami brihaspatim ||",
            beejMantra = "ॐ ग्राम् ग्रीम् ग्राम् सह गुरवे नमः",
            element = "Ether (Akash)",
            day = "Thursday (Guruvaar)",
            gemstone = "Yellow Sapphire (Pukhraj), Topaz",
            metal = "Gold (Swarna)",
            color = "Yellow, Golden, Orange",
            positiveTraits = listOf(
                "Wisdom and knowledge",
                "Spiritual inclination and dharma",
                "Good teaching and advisory abilities",
                "Prosperity and abundance",
                "Optimism and positive outlook",
                "Good children and family life"
            ),
            challenges = listOf(
                "Over-indulgence and excess",
                "Stubbornness and dogmatic behavior",
                "Liver and weight-related issues",
                "Financial over-extension",
                "False wisdom or misguided beliefs",
                "Procrastination and laziness"
            ),
            rulingDeity = "Brihaspati, Guru",
            yogas = listOf(
                "Hamsa Yoga (exalted placement)",
                "Gaja Kesari Yoga (with Moon)",
                "Dharma Karmadhipati Yoga"
            ),
            afflictions = listOf(
                Affliction(
                    title = "Debilitated in Capricorn",
                    description = "Guru in Capricorn struggles to express faith and generosity, causing pessimism.",
                    indicators = listOf(
                        "Career weighed down by excessive duty",
                        "Skepticism toward mentors",
                        "Liver sluggishness or weight gain"
                    ),
                    remedies = listOf(
                        RemedyCategory(
                            title = "Mantra",
                            items = listOf(
                                "Chant 'Om Gram Grim Graum Sah Gurave Namah' Thursdays",
                                "Recite Sri Dakshinamurthy Stotram"
                            )
                        ),
                        RemedyCategory(
                            title = "Charity",
                            items = listOf(
                                "Donate turmeric, chickpeas, or saffron to Brahmins",
                                "Sponsor education or spiritual retreats"
                            )
                        ),
                        RemedyCategory(
                            title = "Sadhana",
                            items = listOf(
                                "Observe Guruvar fast with sattvic food",
                                "Perform Guru Puja with yellow flowers"
                            )
                        )
                    )
                ),
                Affliction(
                    title = "Jupiter-Rahu conjunct",
                    description = "When Guru meets Rahu, wisdom can be overshadowed by ambition or dubious gurus.",
                    indicators = listOf(
                        "Overindulgence in material desires",
                        "Confusion about teachers or belief systems",
                        "Financial overextension"
                    ),
                    remedies = listOf(
                        RemedyCategory(
                            title = "Reflection",
                            items = listOf(
                                "Practice svadhyaya (self study) with sacred texts",
                                "Seek guidance from authentic, lineage-based mentors"
                            )
                        ),
                        RemedyCategory(
                            title = "Discipline",
                            items = listOf(
                                "Keep a generosity ledger tracking seva",
                                "Perform weekly satsang or study circle"
                            )
                        )
                    )
                )
            )
        ),
        PlanetRemedy(
            id = "venus",
            name = "Venus",
            sanskritName = "Shukra",
            archetype = "Love, art, comforts",
            attributes = listOf(
                "Symbolizes relationships, creativity, refinement",
                "Controls reproductive health and kidneys",
                "Guides luxury, harmony, and aesthetics"
            ),
            vedaVyasaMantra = "Hima kunda mrinaalaabham daityaanaam paramam gurum |\nSarva shaastra pravaktaaram bhaargavam pranamaamyaham ||",
            beejMantra = "ॐ द्राम् द्रीम् द्राम् सह शुक्राय नमः",
            element = "Water (Jal)",
            day = "Friday (Shukravar)",
            gemstone = "Diamond (Heera), White Sapphire",
            metal = "Silver (Rajat)",
            color = "White, Pink, Light Blue",
            positiveTraits = listOf(
                "Love and harmonious relationships",
                "Artistic and creative abilities",
                "Beauty and aesthetic sense",
                "Material comforts and luxury",
                "Diplomatic and social skills",
                "Good reproductive health"
            ),
            challenges = listOf(
                "Over-indulgence in pleasures",
                "Relationship conflicts and infidelity",
                "Kidney and reproductive issues",
                "Vanity and superficiality",
                "Attachment to material possessions",
                "Sexual misconduct"
            ),
            rulingDeity = "Shukracharya, Lakshmi",
            yogas = listOf(
                "Malavya Yoga (exalted placement)",
                "Ubhayachari Yoga (planets on both sides)",
                "Raj Yoga (with benefics)"
            ),
            afflictions = listOf(
                Affliction(
                    title = "Venus with Ketu",
                    description = "Shukra aligned with Ketu can split between worldly pleasure and ascetic detachment.",
                    indicators = listOf(
                        "Sudden disinterest in relationships",
                        "Artistic block or creative burnout",
                        "Hormonal imbalance"
                    ),
                    remedies = listOf(
                        RemedyCategory(
                            title = "Mantra",
                            items = listOf(
                                "Chant 'Om Shum Shukraya Namah' on Fridays",
                                "Recite Sri Suktam with ghee lamp"
                            )
                        ),
                        RemedyCategory(
                            title = "Beauty Ritual",
                            items = listOf(
                                "Apply natural fragrances like rose or sandalwood",
                                "Keep fresh flowers and cleanse living spaces"
                            )
                        ),
                        RemedyCategory(
                            title = "Charity",
                            items = listOf(
                                "Support artisans, musicians, or women's shelters",
                                "Donate cosmetics or clothing to those in need"
                            )
                        )
                    )
                ),
                Affliction(
                    title = "Venus Retrograde",
                    description = "Relationships revisit past lessons; values restructure themselves.",
                    indicators = listOf(
                        "Old partners reappearing",
                        "Financial reassessment of luxuries",
                        "Kidney or sugar balance issues"
                    ),
                    remedies = listOf(
                        RemedyCategory(
                            title = "Practices",
                            items = listOf(
                                "Journal about personal values and commitments",
                                "Engage in couple's sadhana or shared creative projects"
                            )
                        ),
                        RemedyCategory(
                            title = "Offering",
                            items = listOf(
                                "Offer white sweets or jasmine to Lakshmi",
                                "Wear clean rose quartz after energizing in moonlight"
                            )
                        )
                    )
                )
            )
        ),
        PlanetRemedy(
            id = "saturn",
            name = "Saturn",
            sanskritName = "Shani",
            archetype = "Discipline, karma, endurance",
            attributes = listOf(
                "Teacher of patience, responsibility, long-term goals",
                "Controls bones, teeth, nervous system",
                "Signals karmic debts and structured service"
            ),
            vedaVyasaMantra = "Neelaanjana samaabhaasam ravi putram yamaagrajam |\nChhaayaa maartanda sambhootam tam namaami shanaishcharam ||",
            beejMantra = "ॐ प्राम् प्रीम् प्राम् सह शनैश्चराय नमः",
            element = "Air (Vayu)",
            day = "Saturday (Shanivar)",
            gemstone = "Blue Sapphire (Neelam), Amethyst",
            metal = "Iron (Loha)",
            color = "Black, Dark Blue, Violet",
            positiveTraits = listOf(
                "Discipline and patience",
                "Hard work and perseverance",
                "Justice and fairness",
                "Spiritual maturity",
                "Long-term success and stability",
                "Service to society"
            ),
            challenges = listOf(
                "Delays and obstacles",
                "Depression and pessimism",
                "Bone and joint problems",
                "Poverty and hardships",
                "Loneliness and isolation",
                "Chronic diseases"
            ),
            rulingDeity = "Shani Bhagavan, Yama",
            yogas = listOf(
                "Shasha Yoga (exalted placement)",
                "Vipareeta Raja Yoga (benefic in dusthana)",
                "Kapata Yoga (with malefics)"
            ),
            afflictions = listOf(
                Affliction(
                    title = "Sade Sati",
                    description = "Seven-and-a-half-year transit over natal Moon tests resilience and detachment.",
                    indicators = listOf(
                        "Slow progress despite effort",
                        "Loneliness, depression, or fear of scarcity",
                        "Bone, joint, or nervous tension"
                    ),
                    remedies = listOf(
                        RemedyCategory(
                            title = "Mantra",
                            items = listOf(
                                "Chant 'Om Sham Shanicharaya Namah' Saturdays",
                                "Recite Shani Kavach and Hanuman Chalisa"
                            )
                        ),
                        RemedyCategory(
                            title = "Seva",
                            items = listOf(
                                "Serve the elderly, differently abled, or chronic patients",
                                "Offer sesame oil lamps and black sesame seeds"
                            )
                        ),
                        RemedyCategory(
                            title = "Discipline",
                            items = listOf(
                                "Maintain strict punctuality and simplicity",
                                "Practice pranayama and grounding yoga"
                            )
                        )
                    )
                ),
                Affliction(
                    title = "Saturn-Mars Opposition",
                    description = "Conflicting energies create frustration and sporadic anger.",
                    indicators = listOf(
                        "Stop-start progress on goals",
                        "Muscular tension and fatigue",
                        "Harsh self-criticism"
                    ),
                    remedies = listOf(
                        RemedyCategory(
                            title = "Awareness",
                            items = listOf(
                                "Establish realistic milestones with patience",
                                "Alternate intense workouts with restorative yoga"
                            )
                        ),
                        RemedyCategory(
                            title = "Charity",
                            items = listOf(
                                "Donate footwear, blankets, or iron tools",
                                "Support rehabilitation programs"
                            )
                        )
                    )
                )
            )
        ),
        PlanetRemedy(
            id = "rahu",
            name = "Rahu",
            sanskritName = "Rahu",
            archetype = "Ambition, innovation, obsession",
            attributes = listOf(
                "Shadow planet magnifying desires and unconventional paths",
                "Rules foreign connections, technology, expansion",
                "Triggers karmic acceleration and illusion"
            ),
            vedaVyasaMantra = "Ardha kaayam mahaa veeryam chandraaditya vimardanam |\nSimhikaa garbha sambhootam tam raahum pranamaamyaham ||",
            beejMantra = "ॐ भ्राम् भ्रीम् भ्राम् सह राहवे नमः",
            element = "Air (Vayu)",
            day = "Saturday (with Saturn)",
            gemstone = "Hessonite (Gomed), Smoky Quartz",
            metal = "Lead, Mixed Metals",
            color = "Smoky, Grey, Multicolored",
            positiveTraits = listOf(
                "Innovation and unconventional thinking",
                "Foreign connections and travel",
                "Technological advancement",
                "Sudden gains and opportunities",
                "Breaking social barriers",
                "Psychic and mystical abilities"
            ),
            challenges = listOf(
                "Obsession and addiction",
                "Illusion and deception",
                "Sudden losses and instability",
                "Mental confusion and anxiety",
                "Poison-related issues",
                "Social outcasting"
            ),
            rulingDeity = "Durga, Kali",
            yogas = listOf(
                "Sarpa Yoga (with other planets)",
                "Kalasarpa Yoga (all planets between Rahu-Ketu)",
                "Grahan Yoga (with Sun/Moon)"
            ),
            afflictions = listOf(
                Affliction(
                    title = "Rahu in the 8th house",
                    description = "Deep fears of loss or taboo interests arise, seeking hidden knowledge.",
                    indicators = listOf(
                        "Obsession with secrets or conspiracy",
                        "Sudden gains and losses",
                        "Anxiety around mortality"
                    ),
                    remedies = listOf(
                        RemedyCategory(
                            title = "Mantra",
                            items = listOf(
                                "Chant 'Om Rahave Namah' on Saturdays",
                                "Recite Durga Saptashati during eclipses"
                            )
                        ),
                        RemedyCategory(
                            title = "Detox",
                            items = listOf(
                                "Practice digital fasting and pranayama",
                                "Engage in shadow work with a trusted guide"
                            )
                        ),
                        RemedyCategory(
                            title = "Service",
                            items = listOf(
                                "Support addiction recovery groups",
                                "Offer shelter or food to marginalized communities"
                            )
                        )
                    )
                ),
                Affliction(
                    title = "Rahu Dasha unrest",
                    description = "Major period of Rahu can disrupt focus and anchorings.",
                    indicators = listOf(
                        "Restless relocation or job changes",
                        "Impulsive risk taking",
                        "Allergy flare-ups or air element imbalance"
                    ),
                    remedies = listOf(
                        RemedyCategory(
                            title = "Stability",
                            items = listOf(
                                "Create clear contracts and accountability partners",
                                "Daily earthing: walk barefoot on grass"
                            )
                        ),
                        RemedyCategory(
                            title = "Protection",
                            items = listOf(
                                "Keep a Rahu yantra energized with Durga mantra",
                                "Wear smoky quartz or hessonite after guidance"
                            )
                        )
                    )
                )
            )
        ),
        PlanetRemedy(
            id = "ketu",
            name = "Ketu",
            sanskritName = "Ketu",
            archetype = "Liberation, intuition, detachment",
            attributes = listOf(
                "Shadow planet dissolving ego attachments",
                "Governs mysticism, moksha, sudden awakenings",
                "Associated with immune system and past-life karmas"
            ),
            vedaVyasaMantra = "Palaasha pushpa sankaasham taarakaa graha mastakam |\nRaudram raudraatmakam ghoram tam ketum pranamaamyaham ||",
            beejMantra = "ॐ स्राम् स्रीम् स्राम् सह केतवे नमः",
            element = "Fire (Agni)",
            day = "Tuesday (with Mars)",
            gemstone = "Cat's Eye (Lahsuniya), Tiger's Eye",
            metal = "Iron, Mixed Metals",
            color = "Brown, Grey, Variegated",
            positiveTraits = listOf(
                "Spiritual wisdom and detachment",
                "Intuitive and psychic abilities",
                "Moksha and liberation seeking",
                "Healing and medical knowledge",
                "Past-life memories and insights",
                "Occult and mystical powers"
            ),
            challenges = listOf(
                "Confusion and lack of direction",
                "Sudden losses and separations",
                "Health issues and infections",
                "Mental instability",
                "Isolation and withdrawal",
                "Spiritual ego and fanaticism"
            ),
            rulingDeity = "Ganesha, Matsya Avatar",
            yogas = listOf(
                "Kalasarpa Yoga (with Rahu)",
                "Moksha Trikona positioning",
                "Spiritual Raja Yoga"
            ),
            afflictions = listOf(
                Affliction(
                    title = "Ketu in the 1st house",
                    description = "Identity feels diffused; people experience confusion about their path.",
                    indicators = listOf(
                        "Difficulty articulating goals",
                        "Sensitivity to crowds and bright lights",
                        "Auto-immune tendencies"
                    ),
                    remedies = listOf(
                        RemedyCategory(
                            title = "Mantra",
                            items = listOf(
                                "Chant 'Om Kem Ketave Namah' on Tuesdays",
                                "Recite Ganapati Atharvashirsha to ground"
                            )
                        ),
                        RemedyCategory(
                            title = "Mindfulness",
                            items = listOf(
                                "Maintain routine grounding rituals like abhyanga",
                                "Practice guided meditation with body awareness"
                            )
                        ),
                        RemedyCategory(
                            title = "Service",
                            items = listOf(
                                "Volunteer in animal shelters or hospice",
                                "Donate blankets and simple foods"
                            )
                        )
                    )
                ),
                Affliction(
                    title = "Ketu Mahadasha confusion",
                    description = "During Ketu periods, life restructures to highlight spiritual priorities.",
                    indicators = listOf(
                        "Sudden urge to abandon material pursuits",
                        "Fragmented sleep and vivid dreams",
                        "Minimalist cravings and detachment"
                    ),
                    remedies = listOf(
                        RemedyCategory(
                            title = "Sadhana",
                            items = listOf(
                                "Perform daily Gayatri Japa",
                                "Engage in breath-led yoga nidra for integration"
                            )
                        ),
                        RemedyCategory(
                            title = "Ritual",
                            items = listOf(
                                "Worship Lord Ganesha with durva grass",
                                "Offer saffron rice to spiritual mentors"
                            )
                        )
                    )
                )
            )
        )
    )
}
