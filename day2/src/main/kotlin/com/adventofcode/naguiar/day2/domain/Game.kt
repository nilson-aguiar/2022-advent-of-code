package com.adventofcode.naguiar.day2.domain

data class Game(val playerScore1: PlayerScore, val playerScore2: PlayerScore)

@JvmInline
value class PlayerScore(val score: Int) {
    operator fun plus(playerScore: PlayerScore) =
        PlayerScore(this.score + playerScore.score)
}
