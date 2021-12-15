package day12

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class PathsTest : FunSpec({

    test("findPaths sample") {
        val inputRawLines = ClassLoader.getSystemResource("day12/sample.txt").readText()
        findPaths(inputRawLines) shouldBe 10
    }

    test("findPaths sample2") {
        val inputRawLines = ClassLoader.getSystemResource("day12/sample2.txt").readText()
        findPaths(inputRawLines) shouldBe 19
    }

    test("findPaths sample3") {
        val inputRawLines = ClassLoader.getSystemResource("day12/sample3.txt").readText()
        findPaths(inputRawLines) shouldBe 226
    }

    test("findPaths input") {
        val inputRawLines = ClassLoader.getSystemResource("day12/input.txt").readText()
        findPaths(inputRawLines) shouldBe 3497
    }

    test("findPathsSpecial sample") {
        val inputRawLines = ClassLoader.getSystemResource("day12/sample.txt").readText()
        findPathsSpecial(inputRawLines) shouldBe 36
    }

    test("findPathsSpecial sample3") {
        val inputRawLines = ClassLoader.getSystemResource("day12/sample3.txt").readText()
        findPathsSpecial(inputRawLines) shouldBe 3509
    }

    test("findPathsSpecial input") {
        val inputRawLines = ClassLoader.getSystemResource("day12/input.txt").readText()
        findPathsSpecial(inputRawLines) shouldBe 93686
    }

})
