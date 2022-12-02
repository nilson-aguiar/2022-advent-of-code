package com.adventofcode.naguiar.day2.domain

data class Round(val move1: PlayerMove, val move2: PlayerMove) {

    companion object {
        private const val WIN = 6
        private const val DRAW = 3
    }

    private fun PlayerMove.scoreAgainst(move: PlayerMove): Int {
        if (this == move)
            return this.score + DRAW

        if (this.winAgainst() == move)
            return this.score + WIN

        return this.score
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
