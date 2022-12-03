package com.adventofcode.naguiar.day3.service

import kotlin.streams.toList

class PriorityCalculator private constructor(){

    private val upperCase = "AZ".chars().toList().toIntRange()
    private val lowerCase = "az".chars().toList().toIntRange()

    companion object {
        val INSTANCE = PriorityCalculator()
        private const val UPPERCASE_PRIORITY_START = 27
        private const val LOWERCASE_PRIORITY_START = 1
    }

    fun priority(value: Char) =
        if (upperCase.contains(value.code)) {
            value.code - upperCase.first + UPPERCASE_PRIORITY_START
        } else {
            value.code - lowerCase.first + LOWERCASE_PRIORITY_START
        }

    private fun List<Int>.toIntRange() = IntRange(this.first(), this.last())

}