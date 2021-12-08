package day06

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

internal class Fish_bKtTest : FunSpec ({

    test("run day06b for 256 days") {
        val inputRawLines = ClassLoader.getSystemResource("day06/sample.txt").readText()

        calculateNumberOfFishB(inputRawLines, 256) shouldBe 26984457539uL
    }

})
