package day05

import java.lang.Integer.max
import java.lang.Integer.min

fun main() {
    val inputRawLines = ClassLoader.getSystemResource("day05/input.txt").readText()
        .split("\n").filter{ "" != it.trim() }

    val lines = parseLines(inputRawLines)
    val maxX = lines.maxOf { max(it.x1, it.x2) }
    val maxY = lines.maxOf { max(it.y1, it.y2) }

    val map = Array(maxY + 1) { IntArray(maxX + 1) { 0 } }

    lines.forEach { line ->
        if (line.isHorizontal()) {
            val l = min(line.x1, line.x2) // left side
            val r = max(line.x1, line.x2) // right side
            (l..r).forEach { x -> map[line.y1][x] += 1 } // line from left to right
        } else if (line.isVertical()) {
            val t = min(line.y1, line.y2) // top end
            val b = max(line.y1, line.y2) // bottom end
            (t..b).forEach { y -> map[y][line.x1] += 1 } // line from top to bottom
        } else {
            if (line.x2 > line.x1 && line.y2 > line.y1) { // top left -> bottom right
                paintLine(line, map,
                    { a, b -> a <= b }) { x, y ->
                    Pair(x + 1, y + 1)
                }
            } else if (line.x1 > line.x2 && line.y1 > line.y2) { // bottom right -> top left
                paintLine(line, map,
                    { a, b -> a >= b }) { x, y ->
                    Pair(x - 1, y - 1)
                }
            } else if (line.x1 > line.x2 && line.y2 > line.y1) { // top right -> bottom left
                paintLine(line, map,
                    { a, b -> a >= b }) { x, y ->
                    Pair(x - 1, y + 1)
                }
            } else { // bottom left -> top right
                paintLine(line, map,
                    { a, b -> a <= b }) { x, y ->
                    Pair(x + 1, y - 1)
                }
            }
        }
    }

    map.forEach {
        it.forEach { print(it) }
        println()
    }

    val dangerous = map.flatMap { it.asList() }.count { it >= 2 }
    println("dangerous: $dangerous")
}

private fun paintLine(
    line: Line,
    map: Array<IntArray>,
    compare: (x: Int, x2: Int) -> Boolean,
    move: (x: Int, y: Int) -> Pair<Int, Int>
) {
    var x = line.x1
    var y = line.y1
    while (compare(x, line.x2)) {
        map[y][x] += 1
        val p = move(x, y)
        x = p.first
        y = p.second
    }
}
