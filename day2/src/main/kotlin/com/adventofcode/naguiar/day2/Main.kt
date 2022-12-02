package com.adventofcode.naguiar.day2

import com.adventofcode.naguiar.day2.domain.PlayerMove
import com.adventofcode.naguiar.day2.service.GameScoreCalculator
import com.adventofcode.naguiar.day2.strategy.DynamicStrategyMove
import com.adventofcode.naguiar.day2.strategy.StaticStrategyMove

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