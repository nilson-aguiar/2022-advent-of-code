package com.adventofcode.naguiar.day2.strategy

import com.adventofcode.naguiar.day2.domain.PlayerMove

interface StrategyMove {

    fun y(opponentMove: PlayerMove): PlayerMove
    fun x(opponentMove: PlayerMove): PlayerMove
    fun z(opponentMove: PlayerMove): PlayerMove

    fun toPlayerMove(last: String, opponentMove: PlayerMove): PlayerMove = when (last) {
        "y", "Y" -> y(opponentMove)
        "x", "X" -> x(opponentMove)
        "z", "Z" -> z(opponentMove)
        else -> throw IllegalArgumentException("Invalid Strategy")
    }
}
