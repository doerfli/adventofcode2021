package day01

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class A_SweepKtTest : FunSpec({

    test("calculateIncrements") {
        val input = ClassLoader.getSystemResource("day01/inputsample.txt").readText()

        calculateIncrements(input) shouldBe 7

    }
})
