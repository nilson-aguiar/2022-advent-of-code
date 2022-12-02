package com.adventofcode.naguiar.day2

import com.adventofcode.naguiar.readAsResourceStream
import kotlin.jvm.optionals.getOrNull
import kotlin.time.ExperimentalTime

/**
 * A Y ROCK
 * B X ROCK
 * C X ROCK
 * A Z
 * B Y
 * C X
 * C X
 * C X
 * C X
 */


@OptIn(ExperimentalTime::class)
fun main() {
    println(GameScoreCalculator("input.txt",
        StaticStrategyMove(PlayerMove.PAPER, PlayerMove.ROCK, PlayerMove.SCISSORS)).calculateGameResult())

    println(GameScoreCalculator(
        "input.txt",
        DynamicStrategyMove(
            x = { playerMove -> playerMove.winAgainst() },
            y = { playerMove -> playerMove },
            z = { playerMove -> playerMove.looseAgainst() }
        )).calculateGameResult()
    )

}

class GameScoreCalculator(private val input: String, private val strategy: StrategyMove) {

    fun calculateGameResult(): Game {
        val finalScore = input.readAsResourceStream().lines()
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

interface StrategyMove {
    fun toPlayerMove(last: String, opponentMove: PlayerMove): PlayerMove
}

data class StaticStrategyMove(val y: PlayerMove, val x: PlayerMove, val z: PlayerMove) : StrategyMove {
    override fun toPlayerMove(last: String, opponentMove: PlayerMove): PlayerMove {
        return when (last) {
            "y", "Y" -> y
            "x", "X" -> x
            "z", "Z" -> z
            else -> throw IllegalArgumentException("Invalid Strategy")
        }
    }
}

data class DynamicStrategyMove(
    val y: (PlayerMove) -> PlayerMove,
    val x: (PlayerMove) -> PlayerMove,
    val z: (PlayerMove) -> PlayerMove
) : StrategyMove {
    override fun toPlayerMove(last: String, opponentMove: PlayerMove): PlayerMove {
        return when (last) {
            "y", "Y" -> y.invoke(opponentMove)
            "x", "X" -> x.invoke(opponentMove)
            "z", "Z" -> z.invoke(opponentMove)
            else -> throw IllegalArgumentException("Invalid Strategy")
        }
    }

}

data class Round(val move1: PlayerMove, val move2: PlayerMove) {

    companion object {
        private const val WIN = 6
        private const val DRAW = 3
    }

    private fun PlayerMove.scoreAgainst(move: PlayerMove): Int {
        if (this == move) return this.score + DRAW

        return when (this) {
            PlayerMove.ROCK -> {
                when (move) {
                    PlayerMove.SCISSORS -> this.score + WIN
                    else -> this.score
                }
            }

            PlayerMove.PAPER -> {
                when (move) {
                    PlayerMove.ROCK -> this.score + WIN
                    else -> this.score
                }
            }

            PlayerMove.SCISSORS -> {
                when (move) {
                    PlayerMove.PAPER -> this.score + WIN
                    else -> this.score
                }
            }
        }
    }

    fun calculateScore() = RoundScore(
        PlayerScore(move1.scoreAgainst(move2)), PlayerScore(move2.scoreAgainst(move1))
    )
}

data class RoundScore(val playerScore1: PlayerScore, val playerScore2: PlayerScore) {
    operator fun plus(anotherRoundScore: RoundScore) =
        RoundScore(
            this.playerScore1 + anotherRoundScore.playerScore1,
            this.playerScore2 + anotherRoundScore.playerScore2
        )
}

@JvmInline
value class PlayerScore(val score: Int) {
    operator fun plus(playerScore: PlayerScore) =
        PlayerScore(this.score + playerScore.score)
}

data class Game(val playerScore1: PlayerScore, val playerScore2: PlayerScore)






