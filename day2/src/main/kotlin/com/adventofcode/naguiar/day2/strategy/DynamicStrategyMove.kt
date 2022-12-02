package com.adventofcode.naguiar.day2.strategy

import com.adventofcode.naguiar.day2.domain.PlayerMove

class DynamicStrategyMove(
    private val y: (PlayerMove) -> PlayerMove,
    private val x: (PlayerMove) -> PlayerMove,
    private val z: (PlayerMove) -> PlayerMove
) : StrategyMove {
    override fun y(opponentMove: PlayerMove): PlayerMove = y.invoke(opponentMove)
    override fun x(opponentMove: PlayerMove): PlayerMove = x.invoke(opponentMove)
    override fun z(opponentMove: PlayerMove): PlayerMove = z.invoke(opponentMove)
}
