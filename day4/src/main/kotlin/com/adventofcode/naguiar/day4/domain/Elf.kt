package com.adventofcode.naguiar.day4.domain

data class Elf(val initialCampSection: CampSection, val endCampSection: CampSection) {

    constructor(initialCampSection: String, endCampSection: String) :
            this(CampSection(initialCampSection.toInt()), CampSection(endCampSection.toInt()))

    fun sectionsAsIntRage() = IntRange(initialCampSection.section, endCampSection.section)
}