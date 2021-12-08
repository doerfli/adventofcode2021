package day01

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class B_SweepKtTest : FunSpec({

    test("calculateIncrementWindow") {
        val rawInput = ClassLoader.getSystemResource("day01/inputsample.txt").readText()
        calculateIncrementWindow(rawInput) shouldBe 5
    }
})
