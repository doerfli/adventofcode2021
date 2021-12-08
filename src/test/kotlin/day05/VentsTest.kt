package day05

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class VentsTest : FunSpec({

    test("calculateDangerousAreas") {
        val rawInput = ClassLoader.getSystemResource("day05/sample.txt").readText()
        calculateDangerousAreas(rawInput) shouldBe 5
    }

    test("calculateDangerousAreasInclDiagonals") {
        val rawInput = ClassLoader.getSystemResource("day05/sample.txt").readText()
        calculateDangerousAreasInclDiagonals(rawInput) shouldBe 12
    }
})
