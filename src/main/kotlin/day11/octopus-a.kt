package day11

import util.pop

fun main() {
    val inputRawLines = ClassLoader.getSystemResource("day11/input.txt").readText()
    print(simulateOctopusSteps(inputRawLines, 100))
}

internal fun simulateOctopusSteps(inputRawLines: String, steps: Int): Int {
    val map = parseInput(inputRawLines.split("\n"))

    return (1..steps).map { step ->
        executeStep(map, step)
    }.sum()
}

internal fun executeStep(map: List<List<Octopus>>, step: Int): Int {
    // increment all by one
    map.flatten().forEach { octopus -> octopus.increase() }

    // find octopus that can flash
    val flashingOctopus = mutableListOf<Octopus>()
    flashingOctopus.addAll(map.flatten().filter { it.canFlash(step) })

    var flashes = 0

    while( flashingOctopus.isNotEmpty()) {
        val octopus = flashingOctopus.pop()
        octopus!!.flash(step, map, flashingOctopus)
        flashes += 1
    }

    map.flatten().filter { it.lastFlashedStep == step }.forEach { it.reset() }

    return flashes
}

fun parseInput(inputRawLines: List<String>): List<List<Octopus>> {
    return inputRawLines.mapIndexed { y, line -> line.toCharArray().mapIndexed { x, n -> Octopus(n.digitToInt(), x, y) } }
}

class Octopus(private var energyLevel: Int, val x: Int, val y: Int, var lastFlashedStep: Int = -1) {

    fun increase() {
        energyLevel += 1
    }

    fun canFlash(step: Int): Boolean {
        return energyLevel > 9 && lastFlashedStep < step
    }

    fun flash(step: Int, map: List<List<Octopus>>, flashingOctopus: MutableList<Octopus>) {
        lastFlashedStep = step
        val adjacentOctopus = findAdjacent(map)
        adjacentOctopus.forEach {
            it.increase()
            if (it.canFlash(step) && ! flashingOctopus.contains(it)) {
                flashingOctopus.add(it)
            }
        }
    }

    private fun findAdjacent(map: List<List<Octopus>>): List<Octopus> {
        val maxX = 9
        val maxY = 9
        val adjacent = mutableListOf<Octopus>()
        if (x > 0 && y > 0) {
            adjacent.add(map[y - 1][x - 1])
        }
        if (y > 0) {
            adjacent.add(map[y - 1][x])
        }
        if (x < maxX && y > 0) {
            adjacent.add(map[y - 1][x + 1])
        }
        if (x > 0) {
            adjacent.add(map[y][x - 1])
        }
        if (x < maxX) {
            adjacent.add(map[y][x + 1])
        }
        if (x > 0 && y < maxY) {
            adjacent.add(map[y + 1][x - 1])
        }
        if (y < maxY) {
            adjacent.add(map[y + 1][x])
        }
        if (x < maxX && y < maxY) {
            adjacent.add(map[y + 1][x + 1])
        }

        return adjacent
    }

    fun reset() {
        energyLevel = 0
    }
}