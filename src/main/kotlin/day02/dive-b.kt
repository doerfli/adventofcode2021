package day02

fun main() {
    val rawInput = ClassLoader.getSystemResource("day02/input.txt").readText()
    calculateCourseWithAim(rawInput)
}

internal fun calculateCourseWithAim(rawInput: String): Int {
    val lines = rawInput
        .split("\n")

    var pos = 0
    var depth = 0
    var aim = 0

    lines.map { it.split(" ") }
        .forEach { t ->
            val cmd = t[0]
            val n = t[1].toInt()
            when (cmd) {
                "down" -> aim += n
                "up" -> aim -= n
                "forward" -> {
                    pos += n
                    depth += aim * n
                }
            }
        }

    println("pos: $pos - depth: $depth -> ${pos * depth}")
    return pos * depth
}
