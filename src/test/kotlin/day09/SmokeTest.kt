package day09

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class SmokeTest : FunSpec({

    test("lowPointRisk sample") {
        val inputRawLines = ClassLoader.getSystemResource("day09/sample.txt").readText()
        lowPointRisk(inputRawLines) shouldBe 15
    }

    test("lowPointRisk input") {
        val inputRawLines = ClassLoader.getSystemResource("day09/input.txt").readText()
        lowPointRisk(inputRawLines) shouldBe 545
    }

})
