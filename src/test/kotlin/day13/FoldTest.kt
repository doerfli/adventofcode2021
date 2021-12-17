package day13

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class FoldTest : FunSpec({

    test("foldFirst sample") {
        val inputRawLines = ClassLoader.getSystemResource("day13/sample.txt").readText()
        foldFirst(inputRawLines) shouldBe  17
    }

    test("foldFirst input") {
        val inputRawLines = ClassLoader.getSystemResource("day13/input.txt").readText()
        foldFirst(inputRawLines) shouldBe  618
    }

    test("foldAll sample") {
        val inputRawLines = ClassLoader.getSystemResource("day13/sample.txt").readText()
        foldAll(inputRawLines) shouldBe  16
    }

    test("foldAll input") {
        val inputRawLines = ClassLoader.getSystemResource("day13/input.txt").readText()
        foldAll(inputRawLines) shouldBe  98
    }
})
