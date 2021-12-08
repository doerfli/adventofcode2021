package day07

import kotlin.math.abs

fun main() {
    val inputRawLines = ClassLoader.getSystemResource("day07/input.txt").readText()
    print(calculateLeastFuel(inputRawLines))
}

internal fun calculateLeastFuel(inputRawLines: String): Long {
    val initialCrabPositions = inputRawLines.split(",").map { it.toLong() }
    val minPos = initialCrabPositions.minOf { it }
    val maxPos = initialCrabPositions.maxOf { it }

    var bestPos = -1L
    var bestFuel = Long.MAX_VALUE

    (minPos..maxPos).forEach { targetPos ->
        val totalFuel = initialCrabPositions
            .map { initialPos -> calculateFuelConsumption(initialPos, targetPos) }
            .sumOf { it }

        if (totalFuel < bestFuel) {
            bestFuel = totalFuel
            bestPos = targetPos
        }
    }

    return bestFuel
}

fun calculateFuelConsumption(initialPos: Long, targetPos: Long): Long {
    return abs(targetPos - initialPos)
}
