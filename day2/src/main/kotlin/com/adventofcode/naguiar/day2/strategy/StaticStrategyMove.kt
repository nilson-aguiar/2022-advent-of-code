package com.adventofcode.naguiar.day2.strategy

import com.adventofcode.naguiar.day2.domain.PlayerMove


class StaticStrategyMove(
    private val y: PlayerMove,
    private val x: PlayerMove,
    private val z: PlayerMove
) : StrategyMove {
    override fun y(opponentMove: PlayerMove): PlayerMove = y
    override fun x(opponentMove: PlayerMove): PlayerMove = x
    override fun z(opponentMove: PlayerMove): PlayerMove = z
}
