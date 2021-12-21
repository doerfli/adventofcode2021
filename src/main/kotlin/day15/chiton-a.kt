package day15

import util.pop
import util.push

fun main() {
    val inputRawLines = ClassLoader.getSystemResource("day15/input.txt").readText()
    print(findPath(inputRawLines))
}

internal fun findPath(inputRawLines: String): Int {
    val map = parseMap(inputRawLines.split("\n"))
    val bestPathValue = Array(map.size) { IntArray(map[0].size) { Int.MAX_VALUE } }
    val maxX = bestPathValue[0].lastIndex
    val maxY = bestPathValue.lastIndex


    val stepsToTake = mutableListOf<NextStep>()
    stepsToTake.push(NextStep(map[maxY][maxX].value, listOf(map[maxY][maxX]), maxX, maxY - 1))
    stepsToTake.push(NextStep(map[maxY][maxX].value, listOf(map[maxY][maxX]), maxX - 1, maxY))

    while (stepsToTake.isNotEmpty()) {
        val nextStep = stepsToTake.pop()!!
        val x = nextStep.x
        val y = nextStep.y
        val pathTaken = nextStep.pathTaken

        if (x == 0 && y == 0) {
            if (nextStep.lastPosValue < bestPathValue[y][x]) {
//                println(nextStep.lastPosValue)
                bestPathValue[y][x] = nextStep.lastPosValue
                stepsToTake.removeAll { it.lastPosValue > nextStep.lastPosValue }
            }
            continue
        }

        val newPosValue = nextStep.lastPosValue + map[y][x].value

        if (newPosValue >= bestPathValue[0][0]) {
            continue
        }
        if (newPosValue >= bestPathValue[y][x]) {
            continue
        }
        bestPathValue[y][x] = newPosValue
        val newPathTaken = pathTaken.toMutableList()
        newPathTaken.add(map[y][x])
        val possibleNextSteps = mutableListOf<NextStep>()
        if (x > 0 && ! pathTaken.contains(map[y][x-1])) {
            possibleNextSteps.add(NextStep(newPosValue, newPathTaken, x - 1, y))
        }
        if (y > 0 && ! pathTaken.contains(map[y-1][x])) {
            possibleNextSteps.add(NextStep(newPosValue, newPathTaken, x, y - 1))
        }
        if (x < map[0].lastIndex && ! pathTaken.contains(map[y][x+1])) {
            possibleNextSteps.add(NextStep(newPosValue, newPathTaken, x + 1, y))
        }
        if (y < map.lastIndex && ! pathTaken.contains(map[y+1][x])) {
            possibleNextSteps.add(NextStep(newPosValue, newPathTaken, x, y + 1))
        }

//        possibleNextSteps.sortedBy { map[y][x].value }.forEach { stepsToTake.push(it) }
        possibleNextSteps.reversed().forEach { stepsToTake.push(it) }
    }

    return bestPathValue.first().first()
}



fun parseMap(lines: List<String>): List<List<Pos>> {
    return lines.mapIndexed { y, line -> line.toCharArray().mapIndexed { x, char -> Pos(char.digitToInt(), x, y) } }
}

data class Pos(val value: Int, val x: Int, val y: Int)

enum class From { LEFT, RIGHT, TOP, BOTTOM}

data class NextStep(val lastPosValue: Int, val pathTaken: List<Pos>, val x: Int, val y: Int)