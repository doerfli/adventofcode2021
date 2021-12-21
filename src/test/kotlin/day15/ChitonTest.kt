package day15

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ChitonTest : FunSpec({

    test("findPath sample") {
        val inputRawLines = ClassLoader.getSystemResource("day15/sample.txt").readText()
        findPath(inputRawLines) shouldBe 40
    }

    test("findPath input") {
        val inputRawLines = ClassLoader.getSystemResource("day15/input.txt").readText()
        findPath(inputRawLines) shouldBe 527
    }

})
