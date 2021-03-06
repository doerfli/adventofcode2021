package day07

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class WhaleTest : FunSpec({

    test("calculateLeastFuel sample") {
        val inputRawLines = ClassLoader.getSystemResource("day07/sample.txt").readText()
        calculateLeastFuel(inputRawLines) shouldBe 37
    }

    test("calculateLeastFuel input") {
        val inputRawLines = ClassLoader.getSystemResource("day07/input.txt").readText()
        calculateLeastFuel(inputRawLines) shouldBe 356179
    }

    test("calculateLeastFuelCrabStyle sample") {
        val inputRawLines = ClassLoader.getSystemResource("day07/sample.txt").readText()
        calculateLeastFuelCrabStyle(inputRawLines) shouldBe 168
    }

    test("calculateLeastFuelCrabStyle input") {
        val inputRawLines = ClassLoader.getSystemResource("day07/input.txt").readText()
        calculateLeastFuelCrabStyle(inputRawLines) shouldBe 99788435L
    }
})
