package com.adventofcode.naguiar.day2.service

import com.adventofcode.naguiar.day2.domain.Game
import com.adventofcode.naguiar.day2.domain.PlayerMove
import com.adventofcode.naguiar.day2.domain.Round
import com.adventofcode.naguiar.day2.strategy.StrategyMove
import com.adventofcode.naguiar.readResourceAsStream
import kotlin.jvm.optionals.getOrNull

class GameScoreCalculator(private val input: String, private val strategy: StrategyMove) {

    fun calculateGameResult(): Game {
        val finalScore = input.readResourceAsStream().lines()
            .parallel()
            .map {
                it.toRound(strategy).calculateScore()
            }
            .reduce { t, u ->
                t + u
            }.getOrNull() ?: throw IllegalArgumentException("Invalid game")

        return Game(
            playerScore1 = finalScore.playerScore1,
            playerScore2 = finalScore.playerScore2
        )
    }

    private fun String.toRound(strategy: StrategyMove): Round {
        val moves = this.split(" ")

        if (moves.size != 2) throw IllegalArgumentException("Invalid round entry")

        val move1 = PlayerMove.byValue(moves.first())
        return Round(
            move1 = move1,
            move2 = strategy.toPlayerMove(moves.last(), move1)
        )
    }

}