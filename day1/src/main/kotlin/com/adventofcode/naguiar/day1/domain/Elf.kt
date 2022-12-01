package com.adventofcode.naguiar.day1.domain

data class Elf(val id: Long, val snacks: List<Snack>) {
    fun snacksCalories() = snacks.sumOf { it.calories }
}

@JvmInline
value class Snack(val calories: Long)