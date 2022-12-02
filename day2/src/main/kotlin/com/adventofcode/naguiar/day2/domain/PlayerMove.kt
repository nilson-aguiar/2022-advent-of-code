package com.adventofcode.naguiar.day2.domain

enum class PlayerMove(val value: String, val score: Int) {
    ROCK("A", 1), PAPER("B", 2), SCISSORS("C", 3);

    fun winAgainst() = when (this) {
        ROCK -> SCISSORS
        PAPER -> ROCK
        SCISSORS -> PAPER
    }

    fun looseAgainst() = when (this) {
        SCISSORS -> ROCK
        ROCK -> PAPER
        PAPER -> SCISSORS
    }

    companion object {

        fun byValue(value: String) =
            PlayerMove.values().firstOrNull { it.value == value } ?: throw IllegalArgumentException(
                "PlayerMove is invalid")
    }
}
