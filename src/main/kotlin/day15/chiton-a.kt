package day15

import java.lang.Integer.min

fun main() {
    val inputRawLines = ClassLoader.getSystemResource("day15/input.txt").readText()
    print(findPath(inputRawLines))
}

internal fun findPath(inputRawLines: String): Int {
    val map = parseMap(inputRawLines.split("\n"))
    val pathValue = Array(map.size) { IntArray(map[0].size) { 0 } }

    for (y in map.indices) {
        for( x in map[y].indices) {
            if (x == 0 && y == 0) {
                continue
            }
            val pos = map[y][x]
            var fromLeft = Int.MAX_VALUE
            var fromTop = Int.MAX_VALUE
            if (x > 0) {
                fromLeft = pathValue[y][x - 1] + pos.value
            }
            if (y > 0) {
                fromTop = pathValue[y - 1][x] + pos.value
            }
            pathValue[y][x] = min(fromLeft, fromTop)
        }
    }

    return pathValue.last().last()
}

fun parseMap(lines: List<String>): List<List<Pos>> {
    return lines.mapIndexed { y, line -> line.toCharArray().mapIndexed { x, char -> Pos(char.digitToInt(), x, y) } }
}

class Pos(val value: Int, val x: Int, val y: Int)