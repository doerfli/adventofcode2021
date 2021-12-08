package day06

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Fish_aKtTest : FunSpec ({

    test("run day06a for 18 days") {
        val inputRawLines = ClassLoader.getSystemResource("day06/sample.txt").readText()

        calculateNumberOfFishA(inputRawLines, 18) shouldBe 26uL
    }

    test("run day06a for 80 days") {
        val inputRawLines = ClassLoader.getSystemResource("day06/sample.txt").readText()

        calculateNumberOfFishA(inputRawLines, 80) shouldBe 5934uL
    }

})
