package day11

fun main() {
    val inputRawLines = ClassLoader.getSystemResource("day11/input.txt").readText()
    print(findStepWhereAllFlash(inputRawLines))
}

internal fun findStepWhereAllFlash(inputRawLines: String): Int {
    val map = parseInput(inputRawLines.split("\n"))

    var step = 0

    do {
        step += 1
        executeStep(map, step)
    } while (! allOctopusFlash(map, step))

    return step
}

fun allOctopusFlash(map: List<List<Octopus>>, step: Int): Boolean {
    return map.flatten().all { it.lastFlashedStep == step }
}
