package day14

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class PolymerTest : FunSpec({

    test("processPolymer sample") {
        val inputRawLines = ClassLoader.getSystemResource("day14/sample.txt").readText()
        processPolymer(inputRawLines) shouldBe 1588
    }

    test("processPolymer input") {
        val inputRawLines = ClassLoader.getSystemResource("day14/input.txt").readText()
        processPolymer(inputRawLines) shouldBe 2447
    }

})
