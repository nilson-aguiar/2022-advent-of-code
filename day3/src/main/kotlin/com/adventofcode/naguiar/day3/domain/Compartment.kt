package com.adventofcode.naguiar.day3.domain

import kotlin.streams.toList

class Compartment(input: String) {
    val items: Set<Int>

    init {
        items = input.chars().toList().toSortedSet()
    }

    operator fun plus(compartment: Compartment): Compartment =
        Compartment(this.revertToInput() + compartment.revertToInput())

    private fun revertToInput(): String = this.items.map { it.toChar() }.joinToString("")

    override fun toString(): String {
        return items.joinToString { "${it.toChar()}" }
    }
}