package day11

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class OctopusTest : FunSpec({

    test("simulateOctopusSteps sample") {
        val inputRawLines = ClassLoader.getSystemResource("day11/sample.txt").readText()
        simulateOctopusSteps(inputRawLines, 100) shouldBe 1656
    }

    test("simulateOctopusSteps input") {
        val inputRawLines = ClassLoader.getSystemResource("day11/input.txt").readText()
        simulateOctopusSteps(inputRawLines, 100) shouldBe 1637
    }

    test("findStepWhereAllFlash sample") {
        val inputRawLines = ClassLoader.getSystemResource("day11/sample.txt").readText()
        findStepWhereAllFlash(inputRawLines) shouldBe 195
    }

    test("findStepWhereAllFlash input") {
        val inputRawLines = ClassLoader.getSystemResource("day11/input.txt").readText()
        findStepWhereAllFlash(inputRawLines) shouldBe 1637
    }

})
