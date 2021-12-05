package day02

fun main() {
    val lines = ClassLoader.getSystemResource("day02/input.txt").readText()
        .split("\n")

    var pos = 0
    var depth = 0

    lines.map { it.split(" ") }
        .forEach { t ->
            val cmd = t[0]
            val n = t[1].toInt()
            when(cmd) {
                "forward" -> pos += n
                "down" -> depth += n
                "up" -> depth -= n
            }
        }

    println("pos: $pos - depth: $depth -> ${pos * depth}")
}
