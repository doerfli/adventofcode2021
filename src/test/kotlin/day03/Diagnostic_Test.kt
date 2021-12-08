package day03

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Diagnostic_Test : FunSpec({

    test("calculatePowerConsumption") {
        val rawInput = ClassLoader.getSystemResource("day03/sample.txt").readText()
        calculatePowerConsumption(rawInput) shouldBe 198
    }

    test("calculateLifeSupportRating") {
        val rawInput = ClassLoader.getSystemResource("day03/sample.txt").readText()
        calculateLifeSupportRating(rawInput) shouldBe 230
    }
})
