package day10

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class SyntaxTest : FunSpec({

    test("syntaxScore sample") {
        val inputRawLines = ClassLoader.getSystemResource("day10/sample.txt").readText()
        syntaxScore(inputRawLines) shouldBe 26397
    }

    test("syntaxScore input") {
        val inputRawLines = ClassLoader.getSystemResource("day10/input.txt").readText()
        syntaxScore(inputRawLines) shouldBe 343863
    }

    test("completionScore sample") {
        val inputRawLines = ClassLoader.getSystemResource("day10/sample.txt").readText()
        completionScore(inputRawLines) shouldBe 288957
    }

    test("completionScore input") {
        val inputRawLines = ClassLoader.getSystemResource("day10/input.txt").readText()
        completionScore(inputRawLines) shouldBe 2924734236L
    }

})
