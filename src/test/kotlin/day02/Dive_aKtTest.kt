package day02

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Dive_aKtTest : FunSpec({

    test("calculateCourse") {
        val rawInput = ClassLoader.getSystemResource("day02/sample.txt").readText()
        calculateCourse(rawInput) shouldBe 150
    }

    test("calculateCourseWithAim") {
        val rawInput = ClassLoader.getSystemResource("day02/sample.txt").readText()
        calculateCourseWithAim(rawInput) shouldBe 900
    }
})
