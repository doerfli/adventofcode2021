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

    val hvLines = lines.filter { it.isHorizontal() || it.isVertical() }
    hvLines.forEach { line ->
        if (line.isHorizontal()) {
            val l = min(line.x1, line.x2) // left side
            val r = max(line.x1, line.x2) // right side
            (l..r).forEach { x -> map[line.y1][x] += 1 } // line from left to right
        } else if (line.isVertical()) {
            val t = min(line.y1, line.y2) // top end
            val b = max(line.y1, line.y2) // bottom end
            (t..b).forEach { y -> map[y][line.x1] += 1 } // line from top to bottom
        }
    }

    map.forEach {
        it.forEach { print(it) }
        println()
    }

    val dangerous = map.flatMap { it.asList() }.count { it >= 2 }
    println("dangerous: $dangerous")
}

fun parseLines(input: List<String>): List<Line> {
    return input.map {
        it.split(" -> ", ",").let { c -> Line(c[0].toInt(), c[1].toInt(), c[2].toInt(), c[3].toInt()) }
    }
}

class Line(val x1: Int, val y1: Int, val x2: Int, val y2: Int) {
    fun isHorizontal(): Boolean {
        return y1 == y2
    }

    fun isVertical(): Boolean {
        return x1 == x2
    }

}
