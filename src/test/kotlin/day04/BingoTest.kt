package day04

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class BingoTest : FunSpec({

    test("calculateBingoScore") {
        val rawInput = ClassLoader.getSystemResource("day04/sample.txt").readText()
        calculateBingoScore(rawInput) shouldBe 4512
    }

    test("calculateBingoScoreLastBoard") {
        val rawInput = ClassLoader.getSystemResource("day04/sample.txt").readText()
        calculateBingoScoreLastBoard(rawInput) shouldBe 1924
    }
})
