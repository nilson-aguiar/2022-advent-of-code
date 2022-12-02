package com.adventofcode.naguiar.day2

import com.adventofcode.naguiar.day2.domain.PlayerMove
import com.adventofcode.naguiar.day2.service.GameScoreCalculator
import com.adventofcode.naguiar.day2.strategy.StaticStrategyMove
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class MainKtTest {

    @Test
    fun `given a sample input and strategy, should output the expected result`() {

        val strategyMove = StaticStrategyMove(PlayerMove.PAPER, PlayerMove.ROCK, PlayerMove.SCISSORS)

        val gameResult = GameScoreCalculator("sample.txt", strategyMove).calculateGameResult()

        assertThat(gameResult.playerScore2.score).isEqualTo(15)
    }

}